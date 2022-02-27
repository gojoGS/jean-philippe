package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.crud.DishCrudServiceFactory;
import com.example.demo.security.user.restaurant.service.RestaurantUserService;
import com.example.demo.ui.restaurant.component.DishCrudComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Route("app/restaurant/dishes")
@Slf4j
public class DishCrudView extends VerticalLayout {
    @Autowired
    public DishCrudView(RestaurantUserService userService, DishCrudServiceFactory dishCrudServiceFactory) {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = "";

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        }

        long restaurantId = -1;

        var user =
                userService.getUser(email);

        if (!user.isPresent()) {
            log.error("fuck up m8");
        } else {
            restaurantId = user.get().getRestaurant().getId();
        }

        add(
                new DishCrudComponent(dishCrudServiceFactory.getDishCrudService(restaurantId))
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
