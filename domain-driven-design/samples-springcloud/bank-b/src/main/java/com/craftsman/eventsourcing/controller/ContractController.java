package com.craftsman.eventsourcing.controller;

import com.craftsman.eventsourcing.es.command.CreateContractCommand;
import com.craftsman.eventsourcing.es.command.DeleteContractCommand;
import com.craftsman.eventsourcing.es.command.TestCommand;
import com.craftsman.eventsourcing.es.command.UpdateContractCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contracts")
@AllArgsConstructor
public class ContractController {

    private final CommandGateway commandGateway;

    @PostMapping
    public Long createContract(@RequestBody @Valid CreateContractCommand command) {
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{id}")
    public void updateContract(@PathVariable("id") Long id, @RequestBody @Valid UpdateContractCommand command) {
        command.setIdentifier(id);
        commandGateway.send(command);
    }

    @PutMapping("/test/{id}")
    public void test(@PathVariable("id") Long id, @RequestBody @Valid TestCommand command) {
        command.setIdentifier(id);
        commandGateway.send(command);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable("id") Long id) {
        commandGateway.send(new DeleteContractCommand(id));
    }
}
