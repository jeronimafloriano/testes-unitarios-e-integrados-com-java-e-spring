package clinic.springframework.petclinic.services.springdatajpa;

import clinic.springframework.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deleteById() {
        service.deleteById(1l);

        verify(vetRepository).deleteById(1l);
    }
}