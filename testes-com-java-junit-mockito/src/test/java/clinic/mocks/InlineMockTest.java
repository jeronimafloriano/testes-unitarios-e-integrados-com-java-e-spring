package clinic.mocks;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class InlineMockTest {

    @Test
    public void testInlineMock(){
        Map mockMap = mock(Map.class);

        mockMap.put("A", 1);

        assertEquals(mockMap.size(), 0);
    }
}
