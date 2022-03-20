package com.example.demo.ui.jeanphilippe.view;

import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/public/restaurants")
public class RestaurantsView extends JeanPhilippeViewBase {
    @Autowired
    RestaurantRepository restaurantRepository;

    public RestaurantsView() {
        super("Home");
    }
}
