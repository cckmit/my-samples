package com.example;

import com.example.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-16
 */
public interface PersonRepository extends PagingAndSortingRepository<Person,Integer> {
}
