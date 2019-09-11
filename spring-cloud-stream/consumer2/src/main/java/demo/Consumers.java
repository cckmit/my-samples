package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-29
 * @see
 * @since 1.0.0
 */
@EnableBinding({Barista.class})
@Slf4j
@EventDriver
public class Consumers {

        @StreamListener(Barista.INPUT)
    //@StreamListener("${test}")
        public void receiveX1(String data) {
            log.info("Data received from customer-3..." + data);
        }
    }

