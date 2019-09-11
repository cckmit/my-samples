package cn.com.yusys;

import cn.com.yusys.command.CreateTransferCommand;
import cn.com.yusys.command.DeducteTransferCommand;
import cn.com.yusys.es.uuid.UIDGenerator;
import cn.com.yusys.event.TransferCreatedEvent;
import cn.com.yusys.event.TransferDeductedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-08
 * @see
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class TransferAggregate implements TransferInterface {
    @AggregateIdentifier
    private Long identifier;

    String fromNO;

    String toNO;

    Double transferAmount;

    private boolean deleted = false;

    @CommandHandler
    public TransferAggregate(CreateTransferCommand command, MetaData metaData, UIDGenerator generator) {
        if (null == command.getIdentifier()) {
            command.setIdentifier(generator.getId());
        }
        AggregateLifecycle.apply(new TransferCreatedEvent(command.getIdentifier(),command.getFromNO(),command.getToNO(),command.getTransferAmount()), metaData);
    }

    @CommandHandler
    private void on(DeducteTransferCommand command, MetaData metaData) {
        AggregateLifecycle.apply(new TransferDeductedEvent(command.getIdentifier(), command.getFromNO(), command.getToNO(), command.getTransferAmount()), metaData);
    }

    @EventSourcingHandler
    private void on(TransferCreatedEvent event){
        this.setIdentifier(event.getIdentifier());
    }

    @EventSourcingHandler
    private void on(TransferDeductedEvent event) {
        this.setIdentifier(event.getIdentifier());
    }


}
