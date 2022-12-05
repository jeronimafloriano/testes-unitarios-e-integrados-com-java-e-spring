package clinic.springframework.petclinic.services.springdatajpa;

import clinic.springframework.petclinic.model.Visit;
import clinic.springframework.petclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitSDJpaServiceTest {

    @Mock
    VisitRepository repository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Find All Test")
    @Test
    void findAllTest(){
        //given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(repository.findAll()).willReturn(visits);

        //when
        Set<Visit> foundVisits = service.findAll();

        //then
        then(repository).should().findAll();
        assertTrue(foundVisits.contains(visit));
        assertThat(foundVisits).hasSize(1);
    }

    @DisplayName("Find By Id Test")
    @Test
    void findByIdTest(){
        //given
        Visit visit = new Visit(1L);
        given(repository.findById(1L)).willReturn(Optional.of(visit));

        //when
        var result = service.findById(1L);

        //then
        then(repository).should().findById(anyLong());
        then(repository).shouldHaveNoMoreInteractions();

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(visit);
    }

    @DisplayName("Save Test")
    @Test
    void saveTest(){
        //given
        Visit visit = new Visit(1L);
        given(repository.save(any(Visit.class))).willReturn(visit);

        //when
        var result = service.save(visit);

        //then
        then(repository).should().save(visit);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(visit);
    }

    @DisplayName("Delete Test")
    @Test
    void deleteTest(){
        //given
        Visit visit = new Visit();

        //when
        service.delete(visit);

        //then
        then(repository).should().delete(visit);
    }

    @DisplayName("Delete By Id Test")
    @Test
    void deleteByIdTest(){
        //given - none

        //when
        service.deleteById(3L);
        service.deleteById(3L);

        //then
        then(repository).should(times(2)).deleteById(3L);
        then(repository).should(never()).deleteById(null);
    }

    @DisplayName("Test 'delete' Throws With Mockito")
    @Test
    void deleteDoThrowTest(){
        doThrow(new RuntimeException("oops")).when(repository).delete(any());

        assertThrows(RuntimeException.class, () -> repository.delete(new Visit()));

        verify(repository).delete(any());
    }

    @DisplayName("Test 'find by id' Throws With BDD Mockito")
    @Test
    void findByIdDoThrowTestMockito(){
        //given
        given(repository.findById(1L)).willThrow(new RuntimeException("oops"));

        //when
        assertThrows(RuntimeException.class, () -> service.findById(1L));

        //then
        then(repository).should().findById(1L);
    }

    @DisplayName("Test 'delete' Throws With BDD Mockito")
    @Test
    void deleteDoThrowTestBDDMockito(){
        //given
        willThrow(new RuntimeException("oops")).given(repository).delete(any());

        //when
        assertThrows(RuntimeException.class, () -> repository.delete(new Visit()));

        //then
        then(repository).should().delete((any()));
    }
}
