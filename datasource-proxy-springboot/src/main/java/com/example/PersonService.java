package com.example;

import com.example.domain.EventPublish;
import com.example.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-16
 */
@Service
@Slf4j
@EventDriver
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Iterable<Person> getPersons(String name){
        log.info("get persons name-------->{} {}",name,personRepository.count());

        return personRepository.findAll();
       // int a = 1/0;
    }

    @EventDriver
    @Transactional
    public EventPublish savePerson(String name){
        Person person = new Person();
        person.setName(name);
        personRepository.save(person);

        return EventPublish.NEW().setEventType("personSaved");

    }
}
