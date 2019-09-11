package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-29
 * @see
 * @since 1.0.0
 */
@EnableBinding
@RestController
@Slf4j
public class SourceWithDynamicDestination {

    AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private BinderAwareChannelResolver resolver;

    //@RequestMapping(path = "/{target}", method = POST, consumes = "*/*")
    @RequestMapping("/{target}")
    public void handleRequest(@PathVariable("target") String target) {
        sendMessage("msg"+count.addAndGet(1), target, "application/json");
    }

    private void sendMessage(String body, String target, Object contentType) {
        resolver.resolveDestination(target).send(MessageBuilder.createMessage(body,
                new MessageHeaders(Collections.singletonMap(MessageHeaders.CONTENT_TYPE, contentType))));
        log.info("send {}",count.get());
    }
}
