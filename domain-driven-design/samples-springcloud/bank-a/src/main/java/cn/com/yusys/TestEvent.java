package cn.com.yusys;

import cn.com.yusys.es.event.AbstractEvent;
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
public class TestEvent extends AbstractEvent {
    String name;

    public TestEvent(Long identifier, String name) {
        super(identifier);
        this.name = name;
    }
}
