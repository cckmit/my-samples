package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-29
 * @see
 * @since 1.0.0
 */
@EnableBinding({Sink.class,Barista.class})
@Slf4j
public class Consumers {


        @StreamListener(Sink.INPUT1)
        public void receive(String data) {
            log.info("Data received from customer-1..." + data);
        }

        @StreamListener(Sink.INPUT2)
        public void receiveX(String data) {
            log.info("Data received from customer-2..." + data);
        }

        @StreamListener(Barista.INPUT)
        public void receiveX1(String data) {
            log.info("Data received from customer-3..." + data);
            //handleDlq();
           // throw new RuntimeException("error tests");
        }


        //error tests
    @Autowired
        RabbitTemplate rabbitTemplate;
        public void handleDlq(){
            String DLQ = "inboundOrders.ordersGroup.dlq";
            org.springframework.amqp.core.Message message = rabbitTemplate.receive(DLQ);
            int i = 0;
            while (message != null) {
                rabbitTemplate.send(message.getMessageProperties().getReceivedRoutingKey(), message);
                log.debug("succeed send QLX message to {}",message.getMessageProperties().getReceivedRoutingKey());
                message = rabbitTemplate.receive(DLQ);
                i++;
            }
        }

    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("--------------Handling ERROR: " + message);
    }


}

