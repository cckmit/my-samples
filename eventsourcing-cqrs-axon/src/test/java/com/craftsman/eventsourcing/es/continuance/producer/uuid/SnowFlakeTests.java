package com.craftsman.eventsourcing.es.continuance.producer.uuid;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.stream.IntStream;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-08-02
 */
@Slf4j
public class SnowFlakeTests {
    @Test
    public void getId(){
        log.info("{}",SnowFlake.MAX_MACHINE_NUM);
        SnowFlake snowFlake = new SnowFlake(1L);
        Long start = System.currentTimeMillis();
        IntStream.range(0,5000000).forEach(i->{
            snowFlake.nextId();
        });

        log.info("cose:{}",System.currentTimeMillis()-start);
    }

    @Test
    public void workId(){}
}
