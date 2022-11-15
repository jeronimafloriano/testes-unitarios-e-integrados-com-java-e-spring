package basicjunit;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectClasses({AssertionsLambda.class, Assumptions.class})
public class SuitesTests {
}

