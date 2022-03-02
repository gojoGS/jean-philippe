package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.details.DetailsService;
import com.example.demo.backend.restaurant.service.details.DetailsServiceFactory;
import com.example.demo.backend.restaurant.service.restaurant.RestaurantService;
import com.example.demo.backend.restaurant.service.restaurant.RestaurantServiceFactory;
import com.example.demo.security.user.password.validation.PasswordValidationService;
import com.example.demo.security.user.restaurant.service.RestaurantUserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Route("app/restaurant/profile")
@Slf4j
public class RestaurantProfileView extends RestaurantViewBase {
    private TextField restaurantName = new TextField();
    private TextArea restaurantDescription = new TextArea();
    private TextField newPassword = new TextField();
    private TextField confirmOldPassword = new TextField();
    private Button saveButton = new Button("Save");
    private Button cancelButton = new Button("Cancel");

//    private final PasswordValidationService passwordValidationService;
    private final DetailsService detailsService;

    @Autowired
    public RestaurantProfileView(RestaurantUserService userService,
                                 DetailsServiceFactory detailsServiceFactory) {
        super("Profile", "Profile");
        setRestaurantId(userService);


        this.detailsService = detailsServiceFactory.get(restaurantId);

        restaurantName.setLabel("Restaurant name");
        restaurantName.setValue(detailsService.getName());
        restaurantDescription.setLabel("Description");
        restaurantDescription.setValue(detailsService   .getDescription());
        newPassword.setLabel("New password");
        confirmOldPassword.setLabel("Old password");

        add(
                restaurantName,
                restaurantDescription,
                newPassword,
                confirmOldPassword,
                new HorizontalLayout(
                        saveButton,
                        cancelButton
                )
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

    }

    protected void setRestaurantId(RestaurantUserService userService) {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = "";

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        }

        var user =
                userService.getUser(email);

        if (!user.isPresent()) {
            log.error("fuck up m8");
        } else {
            this.restaurantId = user.get().getRestaurant().getId();
        }

        log.info(String.format("restaurantId is %d", restaurantId));
    }
}
