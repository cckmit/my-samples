package cn.com.yusys.event;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-07
 * @see
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
public class PaymentSucceedEvent extends PaymentCreatedEvent{
    public PaymentSucceedEvent(Long identifier, String name, double amount) {
        super(identifier, name, amount);
    }
}
