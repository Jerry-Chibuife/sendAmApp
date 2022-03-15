package africa.semicolon.sendAm;

import africa.semicolon.sendAm.controllers.PackageController;
import africa.semicolon.sendAm.controllers.UserController;
import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    private static UserController userController = new UserController();
    private static PackageController packageController = new PackageController();
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        loadOptions();
    }

    private static void loadOptions() {
        String options = """
                -> A for Register
                -> B for Find by Email
                """;
        display(options);
        String input = collectStringInput(options);
        switch (input.toLowerCase(Locale.ROOT)){
            case "a":
                loadRegisterForm();
                break;
            case "b":
                askUserForEmail();
                break;
            default:
                display("get sense");
                loadOptions();
        }
    }

    private static void askUserForEmail() {
        String userEmail = collectStringInput("Enter the email you want to search");
        var response = userController.getUserByEmail(userEmail);
        display(response.toString());
        loadOptions();
    }

    private static void loadRegisterForm(){
        RegisterUserRequest form = new RegisterUserRequest();
        form.setEmailAddress(collectStringInput("Enter your email address"));
        form.setAddress(collectStringInput("Enter your home address"));
        form.setFirstName(collectStringInput("Enter your first name"));
        form.setLastName(collectStringInput("Enter your last name"));
        form.setPhoneNumber(collectStringInput("Enter your phone number"));
        var response = userController.registerNewUser(form);
        display(response.toString());
    }

    private static String collectStringInput(String message) {
        Scanner input = new Scanner(System.in);
        display(message);
        return input.nextLine();
    }

    private static void display(String message) {
        System.out.println(message);
    }
}
