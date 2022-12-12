package clinic.basicjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class RepeatedTests implements ModelRepeatedTests{

    @DisplayName("My Repeated Test")
    @RepeatedTest(value = 5, name = "{displayName} : {currentRepetition} de {totalRepetitions}")
    void repeatedTest(){
        System.out.println("Repetindo");
    }

    @RepeatedTest(5)
    void repeatedTestWithInfo(TestInfo testInfo, RepetitionInfo repetitionInfo){
        System.out.println(testInfo.getDisplayName() + " - " + repetitionInfo.getCurrentRepetition());

    }
}
