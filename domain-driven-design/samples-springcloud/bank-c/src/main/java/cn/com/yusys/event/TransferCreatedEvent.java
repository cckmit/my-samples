package cn.com.yusys.event;

import cn.com.yusys.TransferInterface;
import cn.com.yusys.es.event.AbstractEvent;
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
@Data
public class TransferCreatedEvent extends AbstractEvent implements TransferInterface {

    String fromNO;

    String toNO;

    Double transferAmount;

    public TransferCreatedEvent(Long identifier, String fromNO, String toNO, double amount) {
        super(identifier);
        this.fromNO = fromNO;
        this.toNO = toNO;
        this.transferAmount = amount;
    }
}
