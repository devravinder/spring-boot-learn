package com.paravar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(person.getId()).isNotNull();
        assertThat(person.getName()).isEqualTo("Ravinder");
        assertThat(person.getEmail()).isEqualTo("ravinder@gmail.com").endsWith("@gmail.com");
    }

    @Test
    void shouldThrowExceptionWhenCreatePersonWithDuplicateEmail() {
        String email = UUID.randomUUID()+"@gmail.com";
        personService.create(new Person(null, "Ravinder", email));


        //Assertj assertion
        assertThatThrownBy(()-> personService.create(new Person(null, "Ravinder", email)))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Person with email '"+email+"' already exists");
    }

    @Test
    void showAssertjAwesomeness() {
        String name = "Ravinder Reddy Kothabad";
        int age = 28;
        assertThat(name).startsWith("Ravi");
        assertThat(name).containsIgnoringCase("Reddy");
        assertThat(age).isGreaterThan(25);

        /* ================================================ */
        Person person1 = new Person(1L, "Ravinder", "ravinder@gmail.com");
        Person person2 = new Person(2L, "Reddy", "reddy@gmail.com");
        Person person3 = new Person(1L, "Ravinder", "ravinder@gmail.com");

        assertThat(person1).usingRecursiveComparison().isEqualTo(person3); //  field comparison

        Person person4 = new Person(null, "Ravinder", "ravinder@gmail.com");
        Person person5 = new Person(null, "Ravinder", "ravinder@gmail.com");

        assertThat(person4)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(person5);

        assertThat(person4)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "email")
                .isEqualTo(person5);

        /* ================================================ */
        List<Person> personList = List.of(person1, person2, person4);
        Person person = new Person(2L, "Reddy", "reddy@gmail.com");

        assertThat(personList).contains(person); // by equals & hashcode

        assertThat(person)
                .usingRecursiveComparison()
                .comparingOnlyFields("id")
                .isIn(personList);

    }
}