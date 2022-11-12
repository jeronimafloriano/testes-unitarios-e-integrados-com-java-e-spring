package springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    @Test
    void groupedAssertions(){
        //given
        Person person = new Person(1l, "Jeronima", "Floriano");

        //then
        assertAll("Tests Set",
                () -> assertEquals("Jeronima", person.getFirstName()),
                () -> assertEquals("Floriano", person.getLastName())
        );
    }

    @Test
    void groupedAssertionsMsgs(){
        //given
        Person person = new Person(2l, "Jeronima", "Floriano");

        //then
        assertAll("Tests Set",
                () -> assertEquals("Jeronima", person.getFirstName(), "First Name passed"),
                () -> assertEquals("Floriano", person.getLastName(), "Last Name passed")
        );
    }
}
