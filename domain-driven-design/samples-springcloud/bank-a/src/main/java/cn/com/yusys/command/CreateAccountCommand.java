package cn.com.yusys.command;

import cn.com.yusys.es.event.AbstractCommand;
import cn.com.yusys.AccountInterface;
import lombok.Data;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-08
 * @see
 * @since 1.0.0
 */

@Data
public class CreateAccountCommand extends AbstractCommand implements AccountInterface {

    String cardNo;

    public CreateAccountCommand(Long identifier, String cardNo) {
        super(identifier);
        this.cardNo = cardNo;
    }
}
