package cn.com.yusys.entity;

import cn.com.yusys.es.event.AbstractEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-09
 * @see
 * @since 1.0.0
 */
@Component
@Slf4j
public class TransferViewHandler {
    @Autowired
    TransferViewRepository transferViewRepository;

    @EventHandler
    public void on(AbstractEvent event, DomainEventMessage<AbstractEvent> message) {


        log.info(MessageFormat.format("{0}: {1} , seq: {2}, payload: {3}", message.getType(), message.getAggregateIdentifier(), message.getSequenceNumber(), message.getPayload()));

       // updateContractView(message.getAggregateIdentifier());
    }
}
