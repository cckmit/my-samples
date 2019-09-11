package demo;

import org.springframework.core.env.MapPropertySource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-30
 * @see
 * @since 1.0.0
 */
public class DynamicLoadPropertySource extends MapPropertySource {
    private static Map<String, Object> map = new ConcurrentHashMap<String, Object>(64);

    static {
        map.put("test","inboundOrders");
    }

    public DynamicLoadPropertySource(String name, Map<String, Object> source) {
        super(name, map);
    }


    @Override
    public Object getProperty(String name) {
        return map.get(name);
    }
}
