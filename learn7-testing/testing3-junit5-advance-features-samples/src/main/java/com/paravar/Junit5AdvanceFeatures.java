package com.paravar;

import java.util.List;
import java.util.Optional;

public class Junit5AdvanceFeatures {
    public static void main(String[] args) {
        PersonRepository repo = new PersonRepository();
        PersonService personService = new PersonService(repo);

        personService.create(new Person(null, "Ravinder", "ravinder@gmail.com"));
        personService.create(new Person(null, "Reddy", "reddy@gmail.com"));

        List<Person> people = personService.findAll();
        people.forEach(System.out::println);

        Optional<Person> byEmail = personService.findByEmail("ravinder@gmail.com");
        System.out.println(byEmail);
    }
}