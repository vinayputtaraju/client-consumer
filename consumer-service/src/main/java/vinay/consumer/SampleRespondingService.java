package vinay.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;
import vinay.messagingsdk.channel.SQSRespondingService;
import vinay.messagingsdk.dto.MessageBody;
import vinay.messagingsdk.dto.MessageResponse;

@Service(value = "sampleRespondingService")
@Slf4j
public class SampleRespondingService implements SQSRespondingService {

    @Override
    public MessageResponse respondMessage(Message message, MessageBody messageBody) {
        log.info("message received : {}",messageBody.getRequestBody());
       /* try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            log.error("error", e);
        }*/
        log.info("message responding : {}",messageBody.getRequestBody());
        return MessageResponse.builder().success(true)
                .responseBody("Success : " + messageBody.getRequestBody()).build();
    }
}
