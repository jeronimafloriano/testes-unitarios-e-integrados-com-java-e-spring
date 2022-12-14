package clinic.basicjunit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Tag("basic")
public class TimeOutTest {

    @Test
    void timeOut(){
        assertTimeout(Duration.ofMillis(100), () -> {
            //Thread.sleep(5000);
            Thread.sleep(10);
            System.out.println("Here!");
        });
    }

    @Test
    void timeOutPreempt(){
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            //Thread.sleep(5000);
            Thread.sleep(10);
            System.out.println("Here assertTimeoutPreemptively!");
        });
    }
}
