package com.example.demo.ui.restaurant.view;

import com.example.demo.security.service.AuthDetailsService;
import com.example.demo.ui.common.component.nav.NavBar;
import com.example.demo.ui.common.view.ViewBase;
import com.example.demo.ui.util.BeanUtil;
import com.vaadin.flow.component.html.H1;

import java.util.Optional;


// TOPIC abstract class
public abstract class RestaurantViewBase extends ViewBase {
    // TOPIC why private

    protected final String restaurantName;
    protected final long restaurantId;

    // TOPIC subclass API
    protected RestaurantViewBase(String title, String header) {
        super(title);
        AuthDetailsService detailsService = BeanUtil.getBean(AuthDetailsService.class);

        this.restaurantId = detailsService.getRestaurantId();
        this.restaurantName = detailsService.getRestaurantName();

        var navBar = new NavBar(
                Optional.of(restaurantName),
                NavBar.NavOption.builder().label("Profile").route(RestaurantProfileView.class).build(),
                NavBar.NavOption.builder().label("Menu").route(RestaurantMenuView.class).build(),
                NavBar.NavOption.builder().label("Tables").route(RestaurantTablesView.class).build(),
                NavBar.NavOption.builder().label("End user").route(RestaurantEndUserView.class).build(),
                NavBar.NavOption.builder().label("Staff").route(RestaurantStaffView.class).build(),
                NavBar.NavOption.builder().label("Orders").route(RestaurantOrdersView.class).build()
        );

        add(navBar);
        add(new H1(header));
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}