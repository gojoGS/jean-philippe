package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.crud.DishCrudServiceFactory;
import com.example.demo.security.user.restaurant.service.RestaurantUserService;
import com.vaadin.flow.component.html.H1;
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
    DishCrudServiceFactory dishCrudServiceFactory;

    public DishCrudView(RestaurantUserService userService) {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            log.error((String) principal);
            log.error(principal.getClass().toString());
            log.error("invalid userdetails object");
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
                new H1(String.valueOf(restaurantId))
        );

//        var dishCrudService = dishCrudServiceFactory.getDishCrudService();
//        var crud = new GridCrud<>(Dish.class, dishCrudService);
//        crud.getGrid().setColumns("name", "type", "priceInHuf", "description");
//        crud.getCrudFormFactory().setVisibleProperties("name", "type", "priceInHuf", "description");


        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
