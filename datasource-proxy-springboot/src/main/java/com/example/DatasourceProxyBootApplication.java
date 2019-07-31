package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-16
 */
@SpringBootApplication
@EnableTransactionManagement
public class DatasourceProxyBootApplication {
    public static void main(final String[] args) {
        SpringApplication.run(DatasourceProxyBootApplication.class, args);
    }
}
