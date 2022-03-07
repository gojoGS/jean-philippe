package com.example.demo.ui.restaurant.view;

import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/staff")
public class RestaurantStaffView extends RestaurantViewBase {
    public RestaurantStaffView() {
        super("Staff", "Staff");
    }
}
