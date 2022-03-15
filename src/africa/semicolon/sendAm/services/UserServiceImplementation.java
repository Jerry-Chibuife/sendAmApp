package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.User;
import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.data.repositories.UserRepository;
import africa.semicolon.sendAm.data.repositories.UserRepositoryImplementation;
import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import africa.semicolon.sendAm.exceptions.RegisterFailureException;
import africa.semicolon.sendAm.exceptions.UserNotFoundException;

import java.util.Locale;

public class UserServiceImplementation implements UserService{

    private UserRepository userRepository = new UserRepositoryImplementation();

    @Override
    public RegisterUserResponse register(RegisterUserRequest requestForm) {
        requestForm.setEmailAddress(requestForm.getEmailAddress().toLowerCase(Locale.ROOT));
        if(emailExists(requestForm.getEmailAddress())) throw new RegisterFailureException("Email is not valid");
        User user = new User();
        user.setEmail(requestForm.getEmailAddress());
        user.setAddress(requestForm.getAddress());
        user.setFullName(requestForm.getLastName() + " " + requestForm.getFirstName());
        user.setPhoneNumber(requestForm.getPhoneNumber());

        User savedUser = userRepository.save(user, requestForm.getEmailAddress());

        RegisterUserResponse newResponse = new RegisterUserResponse();
        newResponse.setEmail(savedUser.getEmail());
        newResponse.setFullName(savedUser.getFullName());

        return newResponse;
    }

    private boolean emailExists(String emailAddress) {
        User user = userRepository.findByEmail(emailAddress);
        return user != null;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public FindUserResponse findUserByEmail(String email) {
        email = email.toLowerCase(Locale.ROOT);
        User user = userRepository.findByEmail(email);

        if(user == null) throw new UserNotFoundException(email + " not found");

        FindUserResponse response = new FindUserResponse();

        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());

        return response;
    }
}
