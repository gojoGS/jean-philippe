package com.example.demo.ui.common.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/app/public/signup/restaurant")
public class RestaurantSignupView extends VerticalLayout {
    private H1 userHeader = new H1("User details");
    private TextField username = new TextField("Username");
    private TextField password = new TextField("Password");

    private H1 restaurantHeader = new H1("Restaurant details");
    private TextField restaurantName = new TextField("Name");
    private TextArea restaurantDescription = new TextArea("Description");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");


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

        add(details, buttons);

        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.AROUND);
    }
}
