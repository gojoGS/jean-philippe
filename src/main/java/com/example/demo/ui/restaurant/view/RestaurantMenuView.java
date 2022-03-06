package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.crud.DishCrudServiceFactory;
import com.example.demo.backend.restaurant.service.crud.beverage.BeverageCrudServiceFactory;
import com.example.demo.security.service.AuthDetailsService;
import com.example.demo.ui.restaurant.component.BeverageCrudComponent;
import com.example.demo.ui.restaurant.component.DishCrudComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tabs.PagedTabs;


@Route("app/restaurant/menu")
@Slf4j
public class RestaurantMenuView extends RestaurantViewBase {

    @Autowired
    public RestaurantMenuView(DishCrudServiceFactory dishCrudServiceFactory,
                              BeverageCrudServiceFactory beverageCrudServiceFactory,
                              AuthDetailsService authDetailsService) {
        super("Menu", "Menu", authDetailsService.getRestaurantId());

        var container = new VerticalLayout();
        var tabs = new PagedTabs(container);
        tabs.add("Dishes", new DishCrudComponent(dishCrudServiceFactory.getDishCrudService(restaurantId)), false);
        tabs.add("Beverages", new BeverageCrudComponent(beverageCrudServiceFactory.get(restaurantId)), false);

        this.add(
                tabs,
                container
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

}
