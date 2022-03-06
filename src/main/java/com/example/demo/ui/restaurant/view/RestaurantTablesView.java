package com.example.demo.ui.restaurant.view;

import com.example.demo.security.service.AuthDetailsService;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/tables")
public class RestaurantTablesView extends RestaurantViewBase {
    @Autowired
    public RestaurantTablesView(AuthDetailsService authDetailsService) {
        super("Tables", "Tables", authDetailsService.getRestaurantId());
    }
}
