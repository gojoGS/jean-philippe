package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.beverage.core.Beverage;
import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.restaurant.service.base.EntityServiceAdapter;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
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
    public RestaurantMenuView(EntityServiceFactory<Dish> dishEntityServiceFactory,
                              EntityServiceFactory<Beverage> beverageEntityServiceFactory) {
        super("Menu", "Menu");

        var container = new VerticalLayout();
        var tabs = new PagedTabs(container);
        tabs.add("Dishes", new DishCrudComponent(
                new EntityServiceAdapter<>(dishEntityServiceFactory.get(restaurantId))),
                false
        );
        tabs.add("Beverages", new BeverageCrudComponent(
                new EntityServiceAdapter<>(beverageEntityServiceFactory.get(restaurantId))),
                false
        );

        this.add(
                tabs,
                container
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

}
