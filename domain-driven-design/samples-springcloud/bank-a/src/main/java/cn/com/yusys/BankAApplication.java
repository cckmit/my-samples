package cn.com.yusys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-08
 * @see
 * @since 1.0.0
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"cn.com.yusys","com.examples"})
public class BankAApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankAApplication.class,args);
    }
}