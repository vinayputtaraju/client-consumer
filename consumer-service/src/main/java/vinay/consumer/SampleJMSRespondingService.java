package vinay.consumer;

import jakarta.jms.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vinay.messagingsdk.channel.JMSRespondingService;
import vinay.messagingsdk.dto.MessageBody;
import vinay.messagingsdk.dto.MessageResponse;

@Service(value = "sampleJMSRespondingService")
@Slf4j
public class SampleJMSRespondingService extends JMSRespondingService {

    @Override
    public MessageResponse respondMessage(Message message, MessageBody messageBody) {
        log.info("message JMS received : {}",messageBody.getRequestBody());
       /* try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            log.error("error", e);
        }*/
        log.info("message JMS responding : {}",messageBody.getRequestBody());
        return MessageResponse.builder().success(true)
                .responseBody("Success : " + messageBody.getRequestBody()).build();
    }
}
