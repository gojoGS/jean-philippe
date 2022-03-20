package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.beverage.core.Beverage;
import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.restaurant.service.base.EntityServiceAdapter;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.ui.restaurant.component.CrudComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tabs.PagedTabs;

@Route("app/restaurant/menu")
@Slf4j
public class RestaurantMenuView extends RestaurantViewBase {
    private final String[] dishCrudProperties = {"name", "type", "priceInHuf", "description"};
    private final String[] beverageCrudProperties = {"name", "priceInHuf", "volumeInMililiters", "alcoholic", "diet"};

    @Autowired
    public RestaurantMenuView(EntityServiceFactory<Dish> dishEntityServiceFactory,
                              EntityServiceFactory<Beverage> beverageEntityServiceFactory) {
        super("Menu", "Menu");

        var container = new VerticalLayout();
        var tabs = new PagedTabs(container);

        var dishCrud = new CrudComponent<>(
                new EntityServiceAdapter<>(dishEntityServiceFactory.get(restaurantId)),
                Dish.class,
                dishCrudProperties
        );
        var beverageCrud = new CrudComponent<>(
                new EntityServiceAdapter<>(beverageEntityServiceFactory.get(restaurantId)),
                Beverage.class,
                beverageCrudProperties
        );

        tabs.add("Dishes", dishCrud, false);
        tabs.add("Beverages", beverageCrud, false);

        this.add(
                tabs,
                container
        );
    }

}
