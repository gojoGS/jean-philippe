package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.crud.DishCrudServiceFactory;
import com.example.demo.security.service.AuthDetailsService;
import com.example.demo.ui.restaurant.component.DishCrudComponent;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/menu")
@Slf4j
public class RestaurantMenuView extends RestaurantViewBase {
    @Autowired
    public RestaurantMenuView(DishCrudServiceFactory dishCrudServiceFactory, AuthDetailsService authDetailsService) {
        super("Menu", "Menu", authDetailsService.getUserId());

        add(
                new DishCrudComponent(dishCrudServiceFactory.getDishCrudService(restaurantId))
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

}
