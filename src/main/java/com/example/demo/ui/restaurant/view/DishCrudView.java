package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.dish.service.DishCrudService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route("app/restaurant/dishes")
public class DishCrudView extends VerticalLayout {

    public DishCrudView(DishCrudService dishCrudService) {
        var crud = new GridCrud<>(Dish.class, dishCrudService);
        crud.getGrid().setColumns("name", "type", "priceInHuf", "description");
        crud.getCrudFormFactory().setVisibleProperties("name", "type", "priceInHuf", "description");

        add(
                new H1("Dishes"),
                crud
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
