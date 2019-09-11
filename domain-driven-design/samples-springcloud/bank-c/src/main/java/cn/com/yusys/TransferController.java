package cn.com.yusys;

import cn.com.yusys.command.CreateTransferCommand;
import cn.com.yusys.command.DeducteTransferCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-09
 * @see
 * @since 1.0.0
 */
@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    CommandGateway commandGateway;

    @PostMapping
    public Long createContract(@RequestBody @Valid CreateTransferCommand command) {
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{id}")
    public void updateContract(@PathVariable("id") Long id, @RequestBody @Valid DeducteTransferCommand command) {
        command.setIdentifier(id);
        commandGateway.send(command);
    }
}
