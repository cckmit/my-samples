package com.example;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.function.Consumer;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-16
 */
@Component
public class TransactionUtil {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void doInTransaction(final Consumer<EntityManager> c) {
        c.accept(entityManager);
    }
}
