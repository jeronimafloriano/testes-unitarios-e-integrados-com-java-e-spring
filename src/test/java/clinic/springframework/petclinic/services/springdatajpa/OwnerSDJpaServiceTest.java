package clinic.springframework.petclinic.services.springdatajpa;

import clinic.springframework.petclinic.services.springdatajpa.OwnerSDJpaService;
import org.junit.jupiter.api.*;
import clinic.springframework.petclinic.model.Owner;

import static org.junit.jupiter.api.Assertions.*;

@Tag("service")
class OwnerSDJpaServiceTest {

    private OwnerSDJpaService service;

    @BeforeEach
    void setUp(){
        service = new OwnerSDJpaService(null, null, null);
    }

    @Disabled(value = "Disable until implementation Mocking")
    @Test
    void findByLastName() {
        Owner founder = service.findByLastName("Silva");
    }

    @Test
    void testException() {
        assertThrows(NullPointerException.class, () -> {
            service.findByLastName("Silva");
        });
    }

    @DisplayName("Testing Last Name")
    @Test
    void findAllByLastNameLike() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}