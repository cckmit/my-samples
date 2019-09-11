package cn.com.yusys.event;

import cn.com.yusys.es.event.AbstractEvent;
import com.examples.PaymentInterface;
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
public class PaymentCreatedEvent extends AbstractEvent implements PaymentInterface {
    String name;
    double amount;

    public PaymentCreatedEvent(Long identifier, String name, double amount) {
        super(identifier);
        this.name = name;
        this.amount = amount;
    }
}
