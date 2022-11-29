package clinic.mocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationMockTest {

    @Mock
    Map<String, Integer> mockMap;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAnnotationMock(){
        mockMap.put("A", 2);

        assertEquals(mockMap.size(), 0);
    }
}
