package uuid;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-08-02
 */
@Slf4j
public class SnowFlakeTests {
    @Test
    public void nextId(){

        SnowFlake snowFlake = new SnowFlake(1);

        log.info("{}",snowFlake.nextId());
    }
}
