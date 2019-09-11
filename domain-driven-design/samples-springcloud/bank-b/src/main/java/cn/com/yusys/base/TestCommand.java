package cn.com.yusys.base;

import cn.com.yusys.es.event.AbstractCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-09
 * @see
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class TestCommand extends AbstractCommand {
    String name;

    public TestCommand(Long identifier, String name) {
        super(identifier);
        this.name = name;
    }
}
