package cn.com.yusys.command;

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
public class DeducteAccountCommand extends CreateAccountCommand {
    String targetNO;
    double amount;

    public DeducteAccountCommand(Long identifier, String cardNo, String targetNO, double amount) {
        super(identifier, cardNo);
        this.targetNO = targetNO;
        this.amount = amount;
    }
}
