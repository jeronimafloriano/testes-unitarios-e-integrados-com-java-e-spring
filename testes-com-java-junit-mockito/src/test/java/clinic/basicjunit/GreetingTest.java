package clinic.basicjunit;

import clinic.basicjunit.Greeting;
import org.junit.jupiter.api.*;

@Tag("basic")
class GreetingTest {

    private Greeting greeting;

    @BeforeAll
    public static void beforeAll(){
        System.out.println("1 - Before All");
    }

    @BeforeEach
    void setUp(){
        System.out.println("2 - Before Each");
        greeting = new Greeting();
    }

    @Test
    void helloWorld1(){
        greeting.helloWorld();
    }

    @Test
    void helloWorld2(){
        greeting.helloWorld("Jeronima");
    }

    @AfterEach
    void afterEach(){
        System.out.println("3 - After Each");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("4 - After All");
    }

}
