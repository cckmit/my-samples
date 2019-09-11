package cn.com.yusys.event;

import cn.com.yusys.AccountInterface;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 转帐扣款事件
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-08
 * @see
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
public class AccountDeductedEvent extends AccountCreatedEvent implements AccountInterface {
    String targetNO;
    double amount;

    public AccountDeductedEvent(Long identifier, String cardNo, String targetNO, double amount) {
        super(identifier, cardNo);
        this.targetNO = targetNO;
        this.amount = amount;
    }
}
