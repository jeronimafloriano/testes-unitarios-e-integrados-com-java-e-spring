package basicjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import springframework.sfgpetclinic.junitextensions.TimingExtension;
import springframework.sfgpetclinic.model.OwnerType;

import java.util.stream.Stream;

@ExtendWith(TimingExtension.class)
public class ParameterizedTests {

    @DisplayName("Value Source Tests")
    @ParameterizedTest(name = "{displayName} : {index} - {arguments}")
    @ValueSource(strings = {"Testando", "Junit", "Parameterized"})
    void parameterizedTest(String value){
        System.out.println(value);
    }

    @DisplayName("Enum Source Tests")
    @ParameterizedTest(name = "{displayName} : {index} - {arguments}")
    @EnumSource(OwnerType.class)
    void enumTests(OwnerType ownerType){
        System.out.println(ownerType);
    }

    @DisplayName("Csv Source Tests")
    @ParameterizedTest(name = "{displayName} : {index} - {arguments}")
    @CsvSource({
            "FL, 1, 1",
            "OH, 2, 2",
            "MI, 3, 3"
    })
    void csvSource(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + " - " + val2);
    }

    @DisplayName("Csv File Tests")
    @ParameterizedTest(name = "{displayName} : {index} - {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFileSource(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + " - " + val2);
    }

    @DisplayName("Method Souce Tests")
    @ParameterizedTest(name = "{displayName} : {index} - {arguments}")
    @MethodSource("getArgs")
    void fromMethod(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + " - " + val2);
    }

    static Stream<Arguments> getArgs(){
        return Stream.of(
                Arguments.of("FL", 1, 5),
                Arguments.of("UH", 2, 3),
                Arguments.of("MI", 9, 0));
    }

    @DisplayName("Custom Args Provider Tests")
    @ParameterizedTest(name = "{displayName} : {index} - {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void customArgsProvider(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + " - " + val2);
    }
}
