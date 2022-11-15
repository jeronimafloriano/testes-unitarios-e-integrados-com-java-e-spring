package basicjunit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsLambda {

    @Test
    void lambdaExpressions(){
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        assertTrue(numbers.stream()
                .mapToInt(Integer::intValue)
                .sum() > 5, "A soma deve ser maior que 5!");
    }


    @Test
    void groupedAssertions(){
        int [] numbers = {1,2,3};
        assertAll("numbers",
                () -> assertEquals(numbers[0], 1),
                () -> assertEquals(numbers[1], 2),
                () -> assertEquals(numbers[2], 3)
        );
    }


}
