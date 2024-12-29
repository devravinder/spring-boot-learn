package com.paravar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonServiceTest {

    PersonService personService;

    @BeforeEach
    void setUp() {
        PersonRepository repo = new PersonRepository();
        personService = new PersonService(repo);
    }

    @AfterEach
    void tearDown() {
        personService.deleteAll();
    }

    @Nested
    class CreatePersonTests {
        @Test
        void shouldCreatePersonSuccessfully() {
            Person person = personService.create(new Person(null, "Ravinder", "ravinder@gmail.com"));
            assertNotNull(person.getId());
            assertEquals("Ravinder", person.getName());
            assertEquals("ravinder@gmail.com", person.getEmail());
        }

        @Test
        void shouldFailToCreatePersonWithExistingEmail() {
            String email = UUID.randomUUID() +"@gmail.com";
            personService.create(new Person(null, "Ravinder", email));

            assertThatThrownBy(()-> personService.create(new Person(null, "Ravinder", email)))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Person with email '"+email+"' already exists");
        }
    }

    @Nested
    class FindPersonByEmailTests {
        String email;

        @BeforeEach
        void setUp() {
            email = UUID.randomUUID() +"@gmail.com";
            personService.create(new Person(null, "Ravinder", email));
        }

        @Test
        void shouldGetPersonByEmailWhenExists() {
            Optional<Person> optionalPerson = personService.findByEmail(email);
            assertThat(optionalPerson).isPresent();
        }

        @Test
        void shouldGetEmptyWhenPersonByEmailNotExists() {
            Optional<Person> optionalPerson = personService.findByEmail("random@mail.com");
            assertThat(optionalPerson).isEmpty();
        }
    }
}