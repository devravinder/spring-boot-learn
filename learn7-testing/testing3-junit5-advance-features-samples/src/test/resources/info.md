To run tests from cmd
 1. from root project
  $ ./gradlew :learn7-testing:testing3-junit5-advance-features-samples:test

 2. from this project
  $ ../../gradlew test


Junit has some nice features
=====================================
1. Lifecycle methods ( see: LifecycleCallbacksDemoTest )
   - BeforeAll, AfterAll, BeforeEach, AfterEach

2. Test Instance Lifecycle ( see: TestInstanceLifecycleDemoTest )
   - TestInstance.Lifecycle.PER_CLASS ( to create only once object instance for all tests )
   - TestInstance.Lifecycle.PER_METHOD ( to create new object instance for each test )
                - this is useful to test data isolation, without any side effects

3.   ( see: TestInstanceLifecycleDemoTest )
    - @Tag ( to group tests while running )
       - this is useful to run some test conditionally
          eg: run some tests only in development environment
    - @DisplayName ( to show a meaningful name in test output )

4. Conditional tests ( see: ConditionalTestExecutionDemoTest )
   - @Disabled ( to disable a test )
   - @EnabledIf ( to enable a test conditionally )
   - @DisabledIf ( to disable a test conditionally )
   - some others
      - @EnabledOnOs ( to enable a test based on OS )
      - @EnabledOnJre ( to enable a test based on JRE version )
      - etc

5. Timeout ( see: TestInstanceLifecycleDemoTest )
   - @Timeout ( to set a time limit for a test )

6. Nested tests ( see: PersonServiceTest )
   - @Nested ( to group tests in a Test instance with subclass - just for organization )

7. Parameterized tests ( see: PersonServiceParameterizedTests )
    - @ParameterizedTest  ( to mark a method as Parameterized test )
    - @ValueSource ( to multiple values as test arguments) )
    - @CsvSource ( to pass multiple object values as test arguments )
    - @MethodSource ( to pass a method output as test arguments )
   
8. Parallel execution ( see: junit-platform.properties )
   - we can run tests in parallel using threads or run sequentially in the same thread
   - to enable/disable parallel execution

       junit.jupiter.execution.parallel.enabled = true  ( default is false )

     by adding this property in junit-platform.properties

   - we can configure further
      - to run classes in parallel
         junit.jupiter.execution.parallel.mode.classes.default = same_class ( default is same_thread )

      - to run test methods in parallel
        junit.jupiter.execution.parallel.mode.default = concurrent ( default is same_thread )

