package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

    @Autowired
    OwnerController ownerController;

    @Autowired
    ClinicService clinicService;

    MockMvc mockMvc;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @AfterEach
    void tearDown(){
        reset(clinicService);
    }

    @Test
    void initCreationFormTest() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void processFindFormWithNameNotFound() throws Exception {
        //given(clinicService.findOwnerByLastName("")).willReturn(Lists.emptyList());

        mockMvc.perform(get("/owners")
                        .param("lastName", "Dont Find Me!"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void processFindFormReturnListOfOwners() throws Exception {
        //given
        given(clinicService.findOwnerByLastName("")).willReturn(Lists.newArrayList(new Owner(), new Owner()));

        //when
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"));
        //then
        then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("");
    }

    @Test
    void processFindFormReturnOneResult() throws Exception {
        //given
        Owner owner = new Owner();
        final String findJustOne = "Find Just One";
        owner.setId(5);
        owner.setLastName(findJustOne);

        given(clinicService.findOwnerByLastName(findJustOne)).willReturn(Lists.newArrayList(owner));

        //when
        mockMvc.perform(get("/owners").param("lastName", findJustOne))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/5"));
        //then
        then(clinicService).should().findOwnerByLastName(anyString());
    }

    @Test
    void processCreationFormTestNewOwnerPostValid() throws Exception {
        mockMvc.perform(post("/owners/new")
                    .param("lastName", "Silva")
                    .param("firstName", "Joao")
                    .param("address", "Ipanema, Rua 10, numero 1500")
                    .param("city", "Rio de Janeiro")
                    .param("telephone", "0219633215"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    void processCreationFormWithErrors() throws Exception {
        mockMvc.perform(post("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void processUpdateOwnerFormWithParamInvalid() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", 5)
                    .param("lastName", "Pereira")
                    .param("firstName", "Joao"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void processUpdateOwnerFormWithParamValid() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", 10)
                        .param("lastName", "Pereira")
                        .param("firstName", "Joao")
                        .param("address", "Ipanema, Rua 10, numero 1500")
                        .param("city", "Rio de Janeiro")
                        .param("telephone", "0219633215"))
                        .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }
}
