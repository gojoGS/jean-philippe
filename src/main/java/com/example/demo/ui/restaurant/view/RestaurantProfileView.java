package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.details.DetailsService;
import com.example.demo.backend.restaurant.service.details.DetailsServiceFactory;
import com.example.demo.security.service.AuthDetailsService;
import com.example.demo.security.user.password.validation.PasswordValidationService;
import com.example.demo.security.user.restaurant.service.change.PasswordChangeService;
import com.example.demo.security.user.restaurant.service.change.PasswordChangeServiceFactory;
import com.example.demo.ui.common.component.PasswordFieldBuilder;
import com.example.demo.ui.util.NotificationUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/profile")
@Slf4j
public class RestaurantProfileView extends RestaurantViewBase {
    private TextField restaurantName = new TextField();
    private TextArea restaurantDescription = new TextArea();
    private PasswordField newPassword;
    private PasswordField currentPassword;
    private Button saveButton = new Button("Save");
    private Button resetButton = new Button("Reset");

    private final PasswordValidationService passwordValidationService;
    private final DetailsService detailsService;
    private final PasswordChangeService passwordChangeService;

    @Autowired
    public RestaurantProfileView(DetailsServiceFactory detailsServiceFactory,
                                 AuthDetailsService authDetailsService,
                                 PasswordValidationService passwordValidationService,
                                 PasswordChangeServiceFactory passwordChangeServiceFactory) {
        super("Profile", "Profile");

        this.passwordValidationService = passwordValidationService;
        this.detailsService = detailsServiceFactory.get(restaurantId);
        this.passwordChangeService = passwordChangeServiceFactory.get(authDetailsService.getUserId());

        restaurantName.setLabel("Restaurant name");
        restaurantName.setValue(detailsService.getName());

        restaurantDescription.setLabel("Description");
        restaurantDescription.setValue(detailsService   .getDescription());

        newPassword = new PasswordFieldBuilder()
                .setLabel("New password")
                .setHelperText("Please enter a new password.")
                .setCleatButtonVisible(true)
                .setPattern("^.{8}.*$")
                .setErrorMessage("Invalid password. Your password should be at least 8 characters long")
                .build();

        currentPassword = new PasswordFieldBuilder()
                .setLabel("Current password")
                .setHelperText("Please enter your current password.")
                .setCleatButtonVisible(true)
                .build();

        saveButton.addClickListener(buttonClickEvent -> onSave());
        resetButton.addClickListener(buttonClickEvent -> onReset());

        add(
                restaurantName,
                restaurantDescription,
                newPassword,
                currentPassword,
                new HorizontalLayout(
                        saveButton,
                        resetButton
                )
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void onReset() {
        this.currentPassword.setValue("");
        this.currentPassword.setInvalid(false);

        this.newPassword.setValue("");
        this.newPassword.setInvalid(false);

        this.restaurantDescription.setValue(detailsService.getDescription());
        this.restaurantName.setValue(detailsService.getName());

        NotificationUtil.showInfo("Reset fields");
    }

    private void onSave() {

        if(restaurantName.isEmpty()) {
            this.restaurantName.setInvalid(true);
            NotificationUtil.showError("Please provide a name for your restaurant");
            return;
        }

        detailsService.setName(restaurantName.getValue());

        if (restaurantDescription.isEmpty()) {
            this.restaurantDescription.setInvalid(true);
            NotificationUtil.showError("Please provide a description for your restaurant");
            return;
        }

        detailsService.setDescription(restaurantDescription.getValue());

        if(!newPassword.isEmpty() && currentPassword.isEmpty()) {
            this.currentPassword.setInvalid(true);
            this.newPassword.setInvalid(true);
            NotificationUtil.showError("Please confirm your current password");
            return;
        }

        if(!newPassword.isEmpty() && !currentPassword.isEmpty()) {
            if(!passwordValidationService.isValid(newPassword.getValue())) {
                this.newPassword.setInvalid(true);
                NotificationUtil.showError("Your new password must comply with the general password requirements");
                return;
            }


            if(!passwordChangeService.isCurrentPasswordConfirmationValid(currentPassword.getValue())) {
                this.currentPassword.setInvalid(true);
                NotificationUtil.showError("The password you provided was incorrect");
                return;
            }


            passwordChangeService.changePassword(newPassword.getValue());
        }

        if(newPassword.isEmpty() && !currentPassword.isEmpty()) {
            currentPassword.setValue("");
        }

        NotificationUtil.showSuccess("Saved successfully");
    }
}
