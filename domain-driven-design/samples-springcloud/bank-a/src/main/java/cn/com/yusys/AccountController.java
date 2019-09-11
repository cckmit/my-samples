package cn.com.yusys;

import cn.com.yusys.command.CreateAccountCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-08
 * @see
 * @since 1.0.0
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    CommandGateway commandGateway;

    @PostMapping
    public Long create(@RequestBody @Valid CreateAccountCommand command) {
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{id}")
    public void updateContract(@PathVariable("id") Long id, @RequestBody @Valid TestCommand command) {
        command.setIdentifier(id);
        commandGateway.send(command);
    }

}
