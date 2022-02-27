package com.example.demo.ui.restaurant.component;

import com.example.demo.backend.dish.core.Dish;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.impl.GridCrud;

public class DishCrudComponent extends GridCrud<Dish> {
    public DishCrudComponent(CrudListener<Dish> listener) {
        super(Dish.class, listener);
        getGrid().setColumns("name", "type", "priceInHuf", "description");
        getCrudFormFactory().setVisibleProperties("name", "type", "priceInHuf", "description");
    }
}
