package com.example;

import com.example.domain.EventPublish;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-25
 */
public interface EventPublishRepository extends PagingAndSortingRepository<EventPublish,String > {
}
