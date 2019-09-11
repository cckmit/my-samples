package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-29
 * @see
 * @since 1.0.0
 */
@SpringBootApplication
//@EnableBinding(Source.class)
@EnableBinding(ChannelDefinition.class)
@Slf4j
public class MainRun {


    public static void main(String[] args) {
        SpringApplication.run(MainRun.class, args);
    }

}
