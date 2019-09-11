package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-09-05
 * @see
 * @since 1.0.0
 */
@Service
@EnableScheduling
@Slf4j
public class ProducerService {

    @Autowired
    //Source source;
            ChannelDefinition channelDefinition;


    int i=0;

    @Scheduled(fixedDelay = 1000)
    public void send() {
        i++;
        MessageBuilder messageBuilder = MessageBuilder.withPayload("msg"+i).
                setHeader("eventType", "event1");

        if (i%5==0){
            messageBuilder.setHeader("eventType","event2");
        }

        boolean isSend = channelDefinition.output().send(messageBuilder.build());

        log.info("send:{} - {}",i,isSend);
    }
}
