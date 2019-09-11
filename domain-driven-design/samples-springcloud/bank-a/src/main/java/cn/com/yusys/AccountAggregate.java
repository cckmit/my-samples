package cn.com.yusys;

import cn.com.yusys.command.CreateAccountCommand;
import cn.com.yusys.command.DeducteAccountCommand;
import cn.com.yusys.es.uuid.UIDGenerator;
import cn.com.yusys.event.AccountCreatedEvent;
import cn.com.yusys.event.AccountDeductedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class AccountAggregate implements AccountInterface {
    @AggregateIdentifier
    private Long identifier;

    String cardNo;

    double balance;

    private boolean deleted = false;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command, MetaData metaData, UIDGenerator generator) {
        if (null == command.getIdentifier()) {
            command.setIdentifier(generator.getId());
        }
        AggregateLifecycle.apply(new AccountCreatedEvent(command.getIdentifier(),command.getCardNo()), metaData);
    }

    @CommandHandler
    private void on(DeducteAccountCommand command, MetaData metaData) {
        AggregateLifecycle.apply(new AccountDeductedEvent(command.getIdentifier(), command.getCardNo(), command.getTargetNO(), command.getAmount()), metaData);
    }

    @EventSourcingHandler
    private void on(AccountCreatedEvent event){
        this.setIdentifier(event.getIdentifier());
        this.setBalance(0);
    }

    @EventSourcingHandler
    private void on(AccountDeductedEvent event) {
        this.setIdentifier(event.getIdentifier());
        this.setBalance(getBalance()- event.getAmount());
    }

    @CommandHandler
    private void on(TestCommand command,MetaData metaData){
        AggregateLifecycle.apply(new TestEvent(command.getIdentifier(),command.getName()),metaData);
    }
    @EventSourcingHandler
    private void on(TestEvent event){
        this.cardNo = event.getName();
    }


}
