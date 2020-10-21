package com.testcontainer.integration;

import com.testcontainer.Person;
import com.testcontainer.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestContainersApplicationTests extends AbstractIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void migrate() {
        final List<Person> personList = personRepository.findAll();
        personList.stream().forEach(person -> {
                System.out.println(person.getName());
            }
        );
        System.out.println();
        // migration starts automatically,
        // since Spring Boot runs the Flyway scripts on startup
    }

}
