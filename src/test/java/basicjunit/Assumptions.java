package basicjunit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

public class Assumptions {

    @Test
    void testAssumptionTrue(){
        System.out.println("Teste testAssumptionTrue");
        //assumeTrue("Jeronima".equalsIgnoreCase("Maria"));
        assumeTrue("Jeronima".equalsIgnoreCase("Jeronima"));
    }

    @Test
    void testAssumptionTrue2(){
        assumeTrue(5 > 1 );
        assertEquals(1 + 2, 3);
    }

    @Test
    void testAssumptionFalse(){
        assumeFalse(5 < 1 );
        assertEquals(5 + 2, 7);
    }

    @Test
    void testAssumptionThat(){
        String str = "Uma string qualquer";
        assumingThat(
                str.equals("Just a string"),
                () -> assertEquals(2 + 5, 3)
        );
    }


}
