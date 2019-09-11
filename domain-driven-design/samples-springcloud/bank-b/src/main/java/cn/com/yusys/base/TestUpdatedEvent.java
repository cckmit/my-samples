package cn.com.yusys.base;

import lombok.NoArgsConstructor;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-09
 * @see
 * @since 1.0.0
 */
@NoArgsConstructor
public class TestUpdatedEvent extends TestEvent {
    public TestUpdatedEvent(Long identifier, String name) {
        super(identifier, name);
    }
}
