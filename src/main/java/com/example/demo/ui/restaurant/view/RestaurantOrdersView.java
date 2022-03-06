package com.example.demo.ui.restaurant.view;

import com.example.demo.security.service.AuthDetailsService;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/orders")
public class RestaurantOrdersView extends RestaurantViewBase {
    @Autowired
    public RestaurantOrdersView(AuthDetailsService authDetailsService) {
        super("Orders", "Orders", authDetailsService.getRestaurantId());
    }
}
