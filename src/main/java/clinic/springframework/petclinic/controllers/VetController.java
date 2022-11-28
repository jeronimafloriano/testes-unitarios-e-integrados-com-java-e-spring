package clinic.springframework.petclinic.controllers;

import clinic.springframework.petclinic.fauxspring.Model;
import clinic.springframework.petclinic.services.VetService;

public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    public String listVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
