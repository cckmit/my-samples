package cn.com.yusys.base;

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
@RequestMapping("/test")
public class TestController {
    @Autowired
    CommandGateway commandGateway;

    @PostMapping
    public Long createContract(@RequestBody @Valid TestCommand command) {
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{id}")
    public void updateContract(@PathVariable("id") Long id, @RequestBody @Valid TestUpdatedCommand command) {
        command.setIdentifier(id);
        commandGateway.send(command);
    }
}
