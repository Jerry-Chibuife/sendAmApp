package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;
import africa.semicolon.sendAm.exceptions.RegisterFailureException;
import africa.semicolon.sendAm.exceptions.SendAmAppException;
import africa.semicolon.sendAm.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplementationTest {

    private UserService userService;

    @BeforeEach
    void beforeEachTest() {
        userService = new UserServiceImplementation();
    }

    private RegisterUserRequest createRegisterForm() {
        RegisterUserRequest registerForm = new RegisterUserRequest();
        registerForm.setFirstName("Lotachi");
        registerForm.setLastName("Senior Dev");
        registerForm.setEmailAddress("seniordevengineersolop@gmail.com");
        registerForm.setAddress("cold cold world");
        registerForm.setPhoneNumber("6996696699");
        return registerForm;
    }

    @Test
    void afterRegister_RepositoryContainsOneElement() {
        RegisterUserRequest registerForm = new RegisterUserRequest();
        createRegisterForm();

        userService.register(registerForm);

        assertEquals(1, userService.getRepository().count());
    }

    @Test
    void duplicateEmail_throwsException(){
        RegisterUserRequest lotaForm = createRegisterForm();

        userService.register(lotaForm);

        assertThrows(SendAmAppException.class, ()-> userService.register(lotaForm));
        assertThrows(RegisterFailureException.class, ()-> userService.register(lotaForm));
    }

    @Test
    void duplicateEmailWithDifferentCase_throwsException(){
        RegisterUserRequest lotaForm = createRegisterForm();

        userService.register(lotaForm);
        lotaForm.setEmailAddress("seniordevengineersoloP@gmail.com");

        assertThrows(SendAmAppException.class, ()-> userService.register(lotaForm));
        assertThrows(RegisterFailureException.class, ()-> userService.register(lotaForm));
    }

    @Test
    void registrationComplete_ReturnsCorrectResponse(){
        RegisterUserRequest lota = createRegisterForm();
        var response = userService.register(lota);

        assertEquals("Senior Dev Lotachi", response.getFullName());
        assertEquals("seniordevengineersolop@gmail.com", response.getEmail());
    }

    @Test
    void findRegisteredUserByEmailTest(){
        RegisterUserRequest lota = createRegisterForm();
        userService.register(lota);

        FindUserResponse result = userService.findUserByEmail(lota.getEmailAddress().toLowerCase(Locale.ROOT));

        assertEquals("Senior Dev Lotachi", result.getFullName());
        assertEquals("seniordevengineersolop@gmail.com", result.getEmail());
    }

    @Test
    void findingUnregisteredEmail_ThrowExceptionTest(){
        RegisterUserRequest lota = createRegisterForm();
        userService.register(lota);

        assertThrows(UserNotFoundException.class, ()-> userService.findUserByEmail("alaye@gmail.com"));
    }

    @Test
    void findByUserEmailIsNotCaseSensitiveTest(){
        RegisterUserRequest lota = createRegisterForm();
        userService.register(lota);

        FindUserResponse result = userService.findUserByEmail("SeniorDevEngineerSoloP@gmail.com");

        assertEquals("Senior Dev Lotachi", result.getFullName());
        assertEquals("seniordevengineersolop@gmail.com", result.getEmail());
    }
}