package cn.com.yusys.command;

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
@Data
@NoArgsConstructor
public class DeducteTransferCommand extends CreateTransferCommand {

    public DeducteTransferCommand(Long identifier, String fromNO, String toNO, double amount) {
        super(identifier, fromNO, toNO, amount);
    }
}
