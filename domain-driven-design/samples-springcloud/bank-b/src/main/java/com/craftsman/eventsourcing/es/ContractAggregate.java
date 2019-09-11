package com.craftsman.eventsourcing.es;

import com.craftsman.eventsourcing.es.command.CreateContractCommand;
import com.craftsman.eventsourcing.es.command.DeleteContractCommand;
import com.craftsman.eventsourcing.es.command.TestCommand;
import com.craftsman.eventsourcing.es.command.UpdateContractCommand;
import com.craftsman.eventsourcing.es.event.ContractCreatedEvent;
import com.craftsman.eventsourcing.es.event.ContractDeletedEvent;
import com.craftsman.eventsourcing.es.event.ContractUpdatedEvent;
import com.craftsman.eventsourcing.es.event.TestEvent;
import com.craftsman.eventsourcing.helper.UIDGenerator;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
public class ContractAggregate implements ContractInterface {

    @AggregateIdentifier
    private Long identifier;

    private String name;

    private String partyA;

    private String partyB;

    private boolean deleted = false;

    @CommandHandler
    public ContractAggregate(CreateContractCommand command, MetaData metaData, UIDGenerator generator) {
        if (null == command.getIdentifier()) {
            command.setIdentifier(generator.getId());
        }
        AggregateLifecycle.apply(new ContractCreatedEvent(command.getIdentifier(), command.getName(), command.getPartyA(), command.getPartyB()), metaData);
    }

    @CommandHandler
    private void on(UpdateContractCommand command, MetaData metaData) {
        AggregateLifecycle.apply(new ContractUpdatedEvent(command.getIdentifier(), command.getName(), command.getPartyA(), command.getPartyB()), metaData);
    }

    @CommandHandler
    private void on(DeleteContractCommand command, MetaData metaData) {
        AggregateLifecycle.apply(new ContractDeletedEvent(command.getIdentifier()), metaData);
    }

    @CommandHandler
    private void on(TestCommand command,MetaData metaData){
        AggregateLifecycle.apply(new TestEvent(command.getIdentifier(),command.getName()),metaData);
    }
    @EventSourcingHandler
    private void on(TestEvent event){
        this.name = event.getName();
    }

    @EventSourcingHandler
    private void on(ContractCreatedEvent event) {
        this.setIdentifier(event.getIdentifier());
        this.onUpdate(event);
    }

    @EventSourcingHandler
    private void onUpdate(ContractUpdatedEvent event) {
        this.setName(event.getName());
        this.setPartyA(event.getPartyA());
        this.setPartyB(event.getPartyB());
    }

    @EventSourcingHandler(payloadType = ContractDeletedEvent.class)
    private void on() {
        this.setDeleted(true);
    }
}
