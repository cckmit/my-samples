package juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-09-09
 * @see
 * @since 1.0.0
 */
@Slf4j
public class Tests {
    public static void main(String[] args) {
        CompletableFuture.runAsync((() ->{
            log.info("inner=");
        }));
        log.info("outer==");
    }
}
