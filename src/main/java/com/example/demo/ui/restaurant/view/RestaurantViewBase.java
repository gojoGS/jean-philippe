package com.example.demo.ui.restaurant.view;

import com.example.demo.security.service.AuthDetailsService;
import com.example.demo.ui.common.component.NavBar;
import com.example.demo.ui.util.BeanUtil;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HasDynamicTitle;
import lombok.Setter;

import java.util.Optional;


// TOPIC abstract class
public abstract class RestaurantViewBase extends VerticalLayout implements HasDynamicTitle {
    // TOPIC why private
    @Setter
    protected String title;
    protected final long restaurantId;
    protected final String restaurantName;

    // TOPIC subclass API
    protected RestaurantViewBase(String title, String header) {
        AuthDetailsService detailsService = BeanUtil.getBean(AuthDetailsService.class);

        this.title = title;
        this.restaurantId = detailsService.getRestaurantId();
        this.restaurantName = detailsService.getRestaurantName();

        var navBar = new NavBar(
                Optional.of(restaurantName),
                NavBar.NavOption.builder().label("Profile").route(RestaurantProfileView.class).build(),
                NavBar.NavOption.builder().label("Menu").route(RestaurantMenuView.class).build(),
                NavBar.NavOption.builder().label("Tables").route(RestaurantTablesView.class).build(),
                NavBar.NavOption.builder().label("Staff").route(RestaurantStaffView.class).build(),
                NavBar.NavOption.builder().label("Orders").route(RestaurantOrdersView.class).build()
        );

        add(navBar);
        add(new H1(header));
    }

    @Override
    public String getPageTitle() {
        // TOPIC String.format performance benchmark study
        return String.format("JP | %s", title);
    }
}