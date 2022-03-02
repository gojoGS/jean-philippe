package com.example.demo.ui.restaurant.view;

import com.example.demo.security.service.AuthDetailsService;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant")
public class RestaurantHomeView extends RestaurantViewBase {
    @Autowired
    public RestaurantHomeView(AuthDetailsService authDetailsService) {
        super("Home", "TODO restaurant name", authDetailsService.getUserId());
    }
}
