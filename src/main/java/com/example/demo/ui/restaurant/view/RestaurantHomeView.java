package com.example.demo.ui.restaurant.view;

import com.vaadin.flow.router.Route;

@Route("app/restaurant")
public class RestaurantHomeView extends RestaurantViewBase {
    public RestaurantHomeView() {
        super("Home", "");
        this.setTitle(this.restaurantName);
    }
}
