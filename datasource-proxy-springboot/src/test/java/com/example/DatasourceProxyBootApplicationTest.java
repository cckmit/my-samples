package com.example;

import com.example.domain.Person;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceProxyBootApplicationTest {
    @Autowired
    private TransactionUtil txUtil;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @Test
    public void testInsertIsCaughtByTheDatasourceProxy() {
        // given
        Person person = new Person();
        person.setId(2);
        person.setName("Arnold");
        // when
        txUtil.doInTransaction(em -> em.persist(person));
        // then
        assertEquals(1, QueryCountHolder.getGrandTotal().getInsert());
    }

    @Test
    public void testQuery(){
        Person person = new Person();
       // person.setId(2);
        person.setName("Arnold2222");

        personRepository.save(person);

        Person person2 = new Person();
        person2.setName("222");

        personRepository.save(person2);
        personService.getPersons(person.getName());

    }

    @Test
    public void testSave(){
        Person person = new Person();
        // person.setId(2);
        person.setName("Arnold2222");

        personService.savePerson("ads");

    }



}
