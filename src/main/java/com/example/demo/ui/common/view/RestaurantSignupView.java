package com.example.demo.ui.common.view;

import com.example.demo.backend.restaurant.core.RestaurantDetailsDto;
import com.example.demo.security.user.email.EmailValidationrService;
import com.example.demo.security.user.password.encryption.PasswordEncryptionService;
import com.example.demo.security.user.password.validation.PasswordValidationService;
import com.example.demo.security.user.restaurant.core.RestaurantUserDto;
import com.example.demo.security.user.restaurant.service.RestaurantUserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.demo.ui.util.NotificationUtil.showError;
import static com.example.demo.ui.util.NotificationUtil.showSuccess;

@Route("/app/public/signup/restaurant")
@Slf4j
public class RestaurantSignupView extends VerticalLayout {
    @Autowired
    EmailValidationrService emailValidationrService;
    @Autowired
    PasswordValidationService passwordValidationService;
    @Autowired
    PasswordEncryptionService encryptionService;
    @Autowired
    RestaurantUserService userService;

    private H1 userHeader = new H1("User details");
    private TextField username = new TextField("Email");
    private TextField password = new TextField("Password");

    private H1 restaurantHeader = new H1("Restaurant details");
    private TextField restaurantName = new TextField("Name");
    private TextArea restaurantDescription = new TextArea("Description");

    private Button save = new Button("Sign up");
    private Button delete = new Button("Cancel");


    public RestaurantSignupView() {
        var userDetails = new VerticalLayout(userHeader, username, password);
        userDetails.setAlignItems(Alignment.CENTER);
        userDetails.setJustifyContentMode(JustifyContentMode.CENTER);

        var restaurantDetails = new VerticalLayout(restaurantHeader, restaurantName, restaurantDescription);
        restaurantDetails.setAlignItems(Alignment.CENTER);
        restaurantDetails.setJustifyContentMode(JustifyContentMode.CENTER);

        var details = new HorizontalLayout(userDetails, restaurantDetails);
        details.setAlignItems(Alignment.CENTER);
        details.setJustifyContentMode(JustifyContentMode.AROUND);

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        save.addClickListener(buttonClickEvent -> onSave());

        add(details, buttons);

        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.AROUND);
    }

    private void onSave() {
        log.info("attempting registration");
        var email = username.getValue();
        var pass = password.getValue();
        var name = restaurantName.getValue();
        var description = restaurantDescription.getValue();

        if (!emailValidationrService.isValidEmail(email)) {
            showError("Invalid email");
            return;
        }

        if (!passwordValidationService.isValid(pass)) {
            showError("Invalid password");
            return;
        }

        if (name.isEmpty() || description.isEmpty()) {
            showError("Please fill every field");
            return;
        }

        if (userService.isEmailInUse(email)) {
            showError("User already registered");
            return;
        }

        log.info("creating user");
        userService.createNewRestaurantUser(
                new RestaurantUserDto(
                        encryptionService.encrypt(pass),
                        email
                ),
                new RestaurantDetailsDto(
                        name,
                        description
                )
        );

        showSuccess("Successful signup");

        getUI().ifPresent(ui -> {
            ui.navigate("app/public/login");
        });
    }
}
