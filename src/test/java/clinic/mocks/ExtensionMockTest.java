package clinic.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExtensionMockTest {

    @Mock
    Map<String, Integer> mockMap;

    @Test
    void testExtesionMock(){
        mockMap.put("A", 3);

        assertEquals(mockMap.size(), 0);
    }
}
