package cn.com.yusys.es.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.serialization.Revision;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-07
 * @see
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Revision("1.0.0")
public class AbstractEvent {

    @TargetAggregateIdentifier
    private Long identifier;
}
