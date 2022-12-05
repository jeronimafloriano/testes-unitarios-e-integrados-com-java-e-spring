package clinic.springframework.petclinic.controllers;

import clinic.springframework.petclinic.fauxspring.BindingResult;
import clinic.springframework.petclinic.model.Owner;
import clinic.springframework.petclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5  = "redirect:/owners/5";
    @Mock
    OwnerService service;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult bindingResult;

    @Test
    void processCreationFormNoErrorsTest(){
        //given
        Owner owner = new Owner(5L, "Paulo", "Santos");
        given(bindingResult.hasErrors()).willReturn(false);
        given(service.save(any())).willReturn(owner);

        //when
        String viewName = controller.processCreationForm(owner, bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
    }

    @Test
    void processCreationFormHasErrorsTest(){
        //given
        Owner owner = new Owner(1L, "Paulo", "Silva");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String viewName = controller.processCreationForm(owner,bindingResult);

        //then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
    }
}
