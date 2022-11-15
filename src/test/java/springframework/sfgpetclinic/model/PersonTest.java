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
                () -> assertEquals("Jeronima", person.getFirstName(), "First Name Failed"),
                () -> assertEquals("Floriano", person.getLastName(), "Last Name Failed")
        );
    }

    @Test
    void dependencieAssertions(){
        //given
        Owner owner = new Owner(3l, "Marcus", "Souza");
        owner.setCity("Goiania");
        owner.setTelephone("62996635896");

        //then
        assertAll("Tests Dependencies",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Marcus", owner.getFirstName(), "First Name Failed"),
                        () -> assertEquals("Souza", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Goiania", owner.getCity(), "City Failed"),
                        () -> assertEquals("62996635896", owner.getTelephone()))
        );
    }
}
