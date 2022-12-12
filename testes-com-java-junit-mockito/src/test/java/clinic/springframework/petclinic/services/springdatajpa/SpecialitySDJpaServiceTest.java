package clinic.springframework.petclinic.services.springdatajpa;

import clinic.springframework.petclinic.model.Speciality;
import clinic.springframework.petclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void delete() {
        service.delete(new Speciality());
    }

    @Test
    void deleteById(){
        service.deleteById(1l);

        verify(specialtyRepository).deleteById(1l); //Por padr?o verifica se ? chamado 1 vez (times(1))
    }

    @Test
    void deleteByIdTimes(){
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdAtLeastOnce(){ //pelo menos uma vez
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtLeast(){ //pelo menos X vezes
        service.deleteById(1l);
        service.deleteById(1l);
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atLeast(3)).deleteById(1l);
    }

    @Test
    void deleteByIdAtMost(){ //no m?ximo X vezes
        service.deleteById(1l);

        verify(specialtyRepository, atMost(1)).deleteById(1l);
    }

    @Test
    void deleteByIdNever(){
        service.deleteById(1l);

        verify(specialtyRepository, never()).deleteById(5l);
    }

    @Test
    void deleteTest(){
        Speciality speciality = new Speciality();
        service.delete(speciality);

        verify(specialtyRepository).delete(any(Speciality.class));
        verify(specialtyRepository).delete(speciality);
    }

    @Test
    void findByIdTest(){
        Speciality speciality = new Speciality(2L, "Speciality Test");

        when(specialtyRepository.findById(2L)).thenReturn(Optional.of(speciality));

        Speciality foundSpeciality = service.findById(2L);

        verify(specialtyRepository).findById(2L);
        assertThat(foundSpeciality).isNotNull();
        assertThat(foundSpeciality).isEqualTo(speciality);

    }

    @DisplayName("Test whith lambda")
    @Test
    void testSaveLambda(){
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(2L);

        given(specialtyRepository.save(argThat(argument ->
                argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);

        //when
        Speciality returnedSpeciality = service.save(speciality);

        //then
        assertThat(returnedSpeciality.getId()).isEqualTo(2L);

    }

    @DisplayName("Test whith lambda two")
    @Test
    void testSaveLambdaNoMatch(){
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription("Not a match");

        Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(1L);

        given(specialtyRepository.save(argThat(argument ->
                argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpeciality);

        //when
        Speciality returnedSpeciality = service.save(speciality);

        //then
        assertNull(returnedSpeciality);

    }

}