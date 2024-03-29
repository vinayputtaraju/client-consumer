package vinay.client.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vinay.messagingsdk.dto.MessageBody;
import vinay.messagingsdk.dto.MessageRequest;
import vinay.messagingsdk.impl.MessagingServiceBean;

@RestController
@RequestMapping("/message")
@Slf4j
public class SampleController {

    @Resource
    MessagingServiceBean messagingService;

    @GetMapping("/test/{message}")
    public MessageBody getSqsMessage(@PathVariable("message") String message) throws Exception {
        MessageRequest messageRequest = MessageRequest.builder()
                .channelName("sample")
                .messageBody(new MessageBody("", message, message, message)).build();
        return messagingService.sendAndReceiveMessage(messageRequest).messageBody();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String exceptionHandler(Throwable t) {
        log.error("error", t);
        return t.getMessage();
    }
}
