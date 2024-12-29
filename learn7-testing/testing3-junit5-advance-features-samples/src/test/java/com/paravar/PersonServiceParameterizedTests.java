package com.paravar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PersonServiceParameterizedTests {
    PersonService personService;

    @BeforeEach
    void setUp() {
        PersonRepository repo = new PersonRepository();
        personService = new PersonService(repo);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ravinder1", "Ravinder2", "Ravinder3"})
    void shouldCreatePersonUsingValueSourceSuccessfully( String name) {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            personService.create(new Person(null, name, null));
        });
        assertTrue(exception.getMessage()
                .contentEquals("Email is required"));
    }

    @ParameterizedTest
    @CsvSource({
            "Ravinder1,ravinder1@gmail.com",
            "Ravinder2,ravinder2@gmail.com",
            "Ravinder3,ravinder3@gmail.com",
    })
    void shouldCreatePersonUsingCSVSuccessfully(String name, String email) {
        Person person = personService.create(new Person(null, name, email));
        assertNotNull(person.getId());
        assertEquals(name, person.getName());
        assertEquals(email, person.getEmail());
    }

    @ParameterizedTest
    @MethodSource("personPropsProvider")
    void shouldCreatePersonSuccessfully(String name, String email) {
        Person person = personService.create(new Person(null, name, email));
        assertNotNull(person.getId());
        assertEquals(name, person.getName());
        assertEquals(email, person.getEmail());
    }

    static Stream<Arguments> personPropsProvider() {
        return Stream.of(
                arguments("Ravinder", "ravinder@gmail.com"),
                arguments("Reddy", "reddy@gmail.com")
        );
    }

    @ParameterizedTest
    @MethodSource("personObjectsProvider")
    void shouldCreatePersonWithObjectInputSuccessfully(Person personInput) {
        Person person = personService.create(personInput);
        assertNotNull(person.getId());
        assertEquals(personInput.getName(), person.getName());
        assertEquals(personInput.getEmail(), person.getEmail());
    }

    static Stream<Arguments> personObjectsProvider() {
        return Stream.of(
                arguments(new Person(null, "Ram", "ram@gmail.com")),
                arguments(new Person(null, "Bheem", "bheem@gmail.com"))
        );
    }
}
