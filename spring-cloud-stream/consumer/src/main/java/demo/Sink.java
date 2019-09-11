package demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-29
 * @see
 * @since 1.0.0
 */
public interface Sink {
    String INPUT1 = "input1";
    String INPUT2 = "input2";


    @Input(INPUT1)
    SubscribableChannel input1();


    @Input(INPUT2)
    SubscribableChannel input2();
}
