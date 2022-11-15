package basicjunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeated")
public interface ModelRepeatedTests {

    @BeforeEach
    default void informations(TestInfo testInfo, RepetitionInfo repetitionInfo){
        System.out.println("INFORMATIONS: " + testInfo.getDisplayName()
                + " - TOTAL:  " + repetitionInfo.getTotalRepetitions()
                + " - ATUAL: " + repetitionInfo.getCurrentRepetition());
    }
}
