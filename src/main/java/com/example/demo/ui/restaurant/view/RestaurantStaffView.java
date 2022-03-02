package com.example.demo.ui.restaurant.view;

import com.example.demo.security.service.AuthDetailsService;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/staff")
public class RestaurantStaffView extends RestaurantViewBase {
    @Autowired
    public RestaurantStaffView(AuthDetailsService authDetailsService) {
        super("Staff", "Staff", authDetailsService.getUserId());
    }
}
