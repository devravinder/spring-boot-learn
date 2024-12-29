package com.paravar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class PersonServiceTest {

    PersonService personService;

    @BeforeEach
    void setUp() {
        PersonRepository repo = new PersonRepository();
        personService = new PersonService(repo);
    }

    @Test
    void shouldCreatePerson() {
        Person person = personService.create(new Person(null, "Ravinder", "ravinder@gmail.com"));
        assertNotNull(person.getId());
        assertEquals("Ravinder", person.getName());
        assertEquals("ravinder@gmail.com", person.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenCreatePersonWithDuplicateEmail() {
        String email = UUID.randomUUID()+"@gmail.com";
        personService.create(new Person(null, "Ravinder", email));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            personService.create(new Person(null, "Ravinder", email));
        });
        assertTrue(exception.getMessage()
                .contentEquals("Person with email '"+email+"' already exists"));
    }
}