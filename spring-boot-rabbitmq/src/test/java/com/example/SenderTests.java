package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-31
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderTests {

        @Autowired
        private Main.Sender sender;

        @Test
        public void hello() throws Exception {
            IntStream.range(1,1000).forEach(i ->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sender.send(i);
            });

        }
}
