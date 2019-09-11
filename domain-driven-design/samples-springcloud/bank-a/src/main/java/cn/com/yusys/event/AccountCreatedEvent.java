package cn.com.yusys.event;

import cn.com.yusys.es.event.AbstractEvent;
import cn.com.yusys.AccountInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-08
 * @see
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountCreatedEvent extends AbstractEvent implements AccountInterface {

    String cardNo;

    public AccountCreatedEvent(Long identifier, String cardNo) {
        super(identifier);
        this.cardNo = cardNo;
    }
}
