package clinic.springframework.petclinic.controllers;

import clinic.springframework.petclinic.model.Pet;
import clinic.springframework.petclinic.model.Visit;
import clinic.springframework.petclinic.services.PetService;
import clinic.springframework.petclinic.services.VisitService;
import clinic.springframework.petclinic.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

    @Mock
    VisitService visitService;

    //@Mock
    //PetService petService;

    @Spy
    PetMapService petMapService;

    @InjectMocks
    VisitController visitController;

    @Test
    void loadPetsWithVisit(){
        //given
        Pet pet = new Pet(2L);
        Pet pet3 = new Pet(3L);
        Map<String, Object> model = new HashMap<>();

        petMapService.save(pet);
        petMapService.save(pet3);

        given(petMapService.findById(anyLong())).willCallRealMethod();

        //when
        Visit visit = visitController.loadPetWithVisit(2L, model);

        //then
        assertThat(visit.getPet()).isEqualTo(pet);
        assertThat(visit.getPet().getId()).isEqualTo(2L);
        assertThat(visit).isNotNull();
        verify(petMapService, times(1)).findById(anyLong());

    }

    @Test
    void loadPetsWithVisitWithStubbing(){
        //given
        Pet pet = new Pet(2L);
        Pet pet3 = new Pet(3L);
        Map<String, Object> model = new HashMap<>();

        petMapService.save(pet);
        petMapService.save(pet3);

        given(petMapService.findById(anyLong())).willReturn(pet);

        //when
        Visit visit = visitController.loadPetWithVisit(2L, model);

        //then
        assertThat(visit.getPet()).isEqualTo(pet);
        assertThat(visit.getPet().getId()).isEqualTo(2L);
        assertThat(visit).isNotNull();
        verify(petMapService, times(1)).findById(anyLong());

    }
}
