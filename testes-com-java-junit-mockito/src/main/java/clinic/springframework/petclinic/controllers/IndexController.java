package clinic.springframework.petclinic.controllers;

public class IndexController {

    public String index(){

        return "index";
    }

    public String oupsHandler(){
        return "notimplemented";
    }
}
