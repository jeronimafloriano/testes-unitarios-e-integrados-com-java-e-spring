package clinic.springframework.petclinic.controllers;

import clinic.springframework.petclinic.fauxspring.BindingResult;
import clinic.springframework.petclinic.fauxspring.Model;
import clinic.springframework.petclinic.model.Owner;
import clinic.springframework.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5  = "redirect:/owners/5";
    @Mock
    OwnerService service;

    @Mock
    Model model;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @BeforeEach
    void setUp(){
        given(service.findAllByLastNameLike(argumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> ownerList = new ArrayList<>();

                    String name = invocation.getArgument(0);

                    if(name.equals("%Guimaraes%")) {
                        ownerList.add(new Owner(10L, "Bruno", "Guimaraes"));
                        return ownerList;
                    } else if (name.equals("%DontFindMe%")){
                        return ownerList;
                    }else if (name.equals("%FindMe%")) {
                        ownerList.add(new Owner(10L, "Bruno", "Guimaraes"));
                        ownerList.add(new Owner(11L, "Bruno 2", "Guimaraes 2"));
                        return ownerList;
                    }
                    throw new RuntimeException("Invalid Argument");
                });
    }

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

    @Test
    void processFindFormWildcardString(){
        //given
        Owner owner = new Owner(1L, "Bruno", "Guimaraes");
        /*List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(service.findAllByLastNameLike(captor.capture())).willReturn(ownerList);*/

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%Guimaraes%").isEqualToIgnoringCase(argumentCaptor.getValue());
    }

    @Test
    void processFindFormWildcardStringAnnotation(){
        //given
        Owner owner = new Owner(3L, "Bruno", "Guimaraes");
        //List<Owner> ownerList = new ArrayList<>();
        //given(service.findAllByLastNameLike(argumentCaptor.capture())).willReturn(ownerList);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%Guimaraes%").isEqualToIgnoringCase(argumentCaptor.getValue());
        assertThat("redirect:/owners/10").isEqualToIgnoringCase(viewName);
        verifyZeroInteractions(model);
    }

    @Test
    void processFindFormWildcardNotFound(){
        //given
        Owner owner = new Owner(3L, "Bruno", "DontFindMe");

        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        //then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(argumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
        verifyZeroInteractions(model);
    }

    @Test
    void processFindFormWildcardFound(){
        //given
        Owner owner = new Owner(3L, "Bruno", "FindMe");
        InOrder inOrder = Mockito.inOrder(service, model);

        //when
        String viewName = controller.processFindForm(owner, bindingResult, model);

        //then
        assertThat("%FindMe%").isEqualToIgnoringCase(argumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

        //in order verifications
        inOrder.verify(service).findAllByLastNameLike(anyString());
        inOrder.verify(model, times(1)).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);
    }
}
