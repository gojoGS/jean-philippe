package com.example.demo.ui.restaurant.view;

import com.example.demo.ui.common.component.NavBar;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HasDynamicTitle;

import java.util.Optional;


// TOPIC abstract class
public abstract class RestaurantViewBase extends VerticalLayout implements HasDynamicTitle {
    // TOPIC why private
    private final String title;

    // TOPIC subclass API
    protected RestaurantViewBase(String title, String header) {
        var navBar = new NavBar(
                Optional.of("Label"),
                NavBar.NavOption.builder().label("Profile").route(RestaurantProfileView.class).build(),
                NavBar.NavOption.builder().label("Menu").route(RestaurantMenuView.class).build(),
                NavBar.NavOption.builder().label("Tables").route(RestaurantTablesView.class).build(),
                NavBar.NavOption.builder().label("Staff").route(RestaurantStaffView.class).build(),
                NavBar.NavOption.builder().label("Orders").route(RestaurantOrdersView.class).build()
        );

        add(navBar);
        add(new H1(header));

        this.title = title;
    }

    @Override
    public String getPageTitle() {
        // TOPIC String.format performance benchmark study
        return String.format("JP | %s", title);
    }
}