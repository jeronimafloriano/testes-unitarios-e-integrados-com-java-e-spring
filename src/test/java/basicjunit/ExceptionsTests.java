package basicjunit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionsTests {

    @Test
    void deveLançarException(){
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Não suportado!");
        });

        assertEquals("Não suportado!", exception.getMessage());
    }

    @Test
    void assertThrowsException(){
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });

    }


}
