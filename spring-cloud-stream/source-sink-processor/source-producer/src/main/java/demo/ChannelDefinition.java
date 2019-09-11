package demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-09-06
 * @see
 * @since 1.0.0
 */
public interface ChannelDefinition {
    /**
     * Name of the output channel.
     */
    String OUTPUT = "output";

    /**
     * @return output channel
     */
    @Output(OUTPUT)
    MessageChannel output();

    /**
     * Input channel name.
     */
    String INPUT = "input";

    /**
     * @return input channel.
     */
    @Input(INPUT)
    SubscribableChannel input();
}
