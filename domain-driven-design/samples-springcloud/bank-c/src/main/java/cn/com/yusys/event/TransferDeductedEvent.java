package cn.com.yusys.event;

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
public class TransferDeductedEvent extends TransferCreatedEvent{
    public TransferDeductedEvent(Long identifier, String fromNO, String toNO, double amount) {
        super(identifier, fromNO, toNO, amount);
    }
}
