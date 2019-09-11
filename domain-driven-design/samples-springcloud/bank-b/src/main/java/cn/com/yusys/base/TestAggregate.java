package cn.com.yusys.base;

import cn.com.yusys.es.uuid.UIDGenerator;
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
 * @date 2019-08-09
 * @see
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class TestAggregate {
    String name;
    @AggregateIdentifier
    private Long identifier;

    @CommandHandler
    public TestAggregate(TestCommand command, MetaData metaData, UIDGenerator generator) {
        if (null == command.getIdentifier()) {
            command.setIdentifier(generator.getId());
        }
        AggregateLifecycle.apply(new TestEvent(command.getIdentifier(), command.getName()), metaData);
    }
    @EventSourcingHandler
    private void on(TestEvent event){
        this.identifier = event.getIdentifier();
        this.name = event.getName();
    }

    @CommandHandler
    public void on (TestUpdatedCommand command,MetaData metaData) {
        AggregateLifecycle.apply(new TestUpdatedEvent(command.getIdentifier(), command.getName()), metaData);
    }
    @EventSourcingHandler
    private void on(TestUpdatedEvent event) {
        this.setIdentifier(event.getIdentifier());
        this.name = this.name+event.getName();
    }
}
