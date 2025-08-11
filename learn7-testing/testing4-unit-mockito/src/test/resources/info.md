To run tests from cmd
 1. from root project
  $ ./gradlew :learn7-testing:testing4-unit-mockito:test

 2. from this project
  $ ../../gradlew test


====================================================================================
1. Mock testing is required in unit testing, to mock the dependencies/services behavior.

2. we can mock ( see: PersonServiceTest )
   - what to return when the method is called
   - what to throw when the method is called

3. we can verify the mock object behavior inside the unit test object ( see: PersonServiceTest )
   - how many times the method is called
   - in what order

4. ArgumentCaptor is used to capture the argument passed to the method ( see: PersonServiceTest )
   - we can verify the expected changes of the argument

5. Spy vs Mock  ( see: SalesServiceTest )
   - Spy is a real object
      - used if the object is not interacting with any other services
      - used if the object has pure functions ( no side effects )

   - Mock is a fake object
     - used if the object is interacting with other services
     - used if the object has side effect methods/functionalities

6. Best Practices
   - try to mock as less as possible
   - don't verify the behavior of internal / third party libraries ( eg: JpaRepository )
   -  always follow - AAA
      - Arrange, Act, Assert

   - don't mock... what you don't want
       eg: don't mock internal / third party libraries ( eg: JpaRepository )
       - instead write integration tests

   - don't couple the test logic with implementation ( or try to reduce as much as possible )
      eg:  see: PersonServiceTest.updatePerson
          - in the updatePerson method,
             we are depending on the implementation of personService.update()
              by verifying the repo.update() methods is invoked or not

           - try to avoid this kind of coupling


   - define the scope of 'Unit' properly
      - figure out is it single class or group of classes
        - if single class, then unit test with classist approach ( JUnit5 or AssertJ )
        - if group of classes, then mock test

      - always try to keep the unit as independent as possible
            - so we can use more classist approach test then mock test
