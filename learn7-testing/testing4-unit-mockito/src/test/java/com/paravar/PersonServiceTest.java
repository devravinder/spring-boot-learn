package com.paravar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    PersonRepository repo;

    @InjectMocks
    PersonService personService;

    // Without using @ExtendWith(MockitoExtension.class)
    /*
    @BeforeEach
    void setUp() {
        repo = Mockito.mock(PersonRepository.class);
        personService = new PersonService(repo);
    }
    */

    @Test
    void loginSuccess() {
        //**Arrange**


        Person person = new Person(1L, "Ravinder", "ravinder@gmail.com", "ravinder123");
         // when(repo.findByEmailAndPassword("ravinder@gmail.com", "ravinder123")
         // when(repo.findByEmailAndPassword("ravinder@gmail.com", anyString()) // won't work
         // Note: wild card args with fixed value won't work...instead use eq
        when(repo.findByEmailAndPassword(eq("ravinder@gmail.com"), anyString()))
                .thenReturn(Optional.of(person));// return this when the args match


        //**Act**
        String token = personService.login("ravinder@gmail.com", "ravinder123");



        //**Assert**
        assertThat(token).isNotNull();
    }

    @Test
    void loginFailure() {
        when(repo.findByEmailAndPassword("ravinder@gmail.com", "ravinder123"))
               // .thenThrow(new RuntimeException("Invalid email")); // to throw error
                .thenReturn(Optional.empty());

        String token = personService.login("ravinder@gmail.com", "ravinder123");

        assertThat(token).isNull();
    }

    @Test
    void findByEmail() {
        Person person = new Person(1L, "Ravinder", "ravinder@gmail.com", "ravinder123");
        when(repo.findByEmail("ravinder@gmail.com")).thenReturn(Optional.of(person));

        Optional<Person> optionalPerson = personService.findByEmail("ravinder@gmail.com");

        assertThat(optionalPerson).isPresent();
        assertThat(optionalPerson.get().getName()).isEqualTo("Ravinder");
        assertThat(optionalPerson.get().getEmail()).isEqualTo("ravinder@gmail.com");
    }

    @Test
    void shouldCreatePersonSuccessfully() {
        when(repo.findByEmail("ravinder@gmail.com")).thenReturn(Optional.empty());
        when(repo.create(any(Person.class))).thenAnswer(answer -> answer.getArgument(0));

        Person person = personService.create("Ravinder", "RAVINDER@gmail.com", "ravinder123");

        assertEquals("Ravinder", person.getName());
        assertEquals("ravinder@gmail.com", person.getEmail());

        ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(repo).create(argumentCaptor.capture());
        Person value = argumentCaptor.getValue();
        assertEquals("Ravinder", value.getName());
        assertEquals("ravinder@gmail.com", value.getEmail());

        /*
         Note: we are not asserting the argumentCaptor.id, bcz it is coming from repository,
              which is third party library.
              - it is best practice not verify third part library behaviour


        * */

    }

    @Test
    void updatePerson() {
        Person person = new Person(1L, "Ravinder", "ravinder@gmail.com", "ravinder123");

        // to mock void return method
        doNothing().when(repo).update(any(Person.class));

        // to mock throw in void return method
        //doThrow(new RuntimeException("Invalid email")).when(repo).update(any(Person.class));

        personService.update(person);

        verify(repo).update(any(Person.class)); // assert the method is called
        //verify(repo, times(1)).update(any(Person.class));
        //verify(repo, atMostOnce()).update(any(Person.class));
        //verify(repo, atLeastOnce()).update(any(Person.class));
    }
}