package springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import springframework.sfgpetclinic.ControllerTests;
import springframework.sfgpetclinic.fauxspring.Model;
import springframework.sfgpetclinic.fauxspring.ModelMapImp;
import springframework.sfgpetclinic.model.Speciality;
import springframework.sfgpetclinic.model.Vet;
import springframework.sfgpetclinic.services.SpecialtyService;
import springframework.sfgpetclinic.services.VetService;
import springframework.sfgpetclinic.services.map.SpecialityMapService;
import springframework.sfgpetclinic.services.map.VetMapService;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class VetControllerTest implements ControllerTests {

    private SpecialtyService specialityService;
    private VetService vetService;
    private VetController vetController;


    @BeforeEach
    void setUp(){
        specialityService = new SpecialityMapService();
        vetService = new VetMapService(specialityService);
        vetController = new VetController(vetService);

        Set<Speciality > specialities = new HashSet<>();
        Speciality speciality = new Speciality();
        specialities.add(speciality);
        Vet vet1 = new Vet(1L, "Maria", "Silva", specialities);
        Vet vet2 = new Vet(2L, "Paulo", "Silva", specialities);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets(){
        Model model = new ModelMapImp();
        String retorno = vetController.listVets(model);

       Set modelAtributte = (Set) ((ModelMapImp) model).getMap().get("vets");

        assertThat("vets/index").isEqualTo(retorno);
        assertThat(modelAtributte.size()).isEqualTo(2);
    }
}
