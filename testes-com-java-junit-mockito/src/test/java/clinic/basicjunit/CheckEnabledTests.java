package clinic.basicjunit;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

@Tag("basic")
public class CheckEnabledTests {

    @EnabledOnOs(OS.LINUX)
    @Test
    void testMeOnLINUX(){
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMAC(){
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows(){
    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testMeOnJava20(){
    }

    @EnabledIfEnvironmentVariable(named = "USER", matches = "MARIA")
    @Test
    void testMeIfUserIs(){
    }
}
