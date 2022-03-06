package com.example.demo.ui.restaurant.component;

import com.example.demo.backend.beverage.core.Beverage;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.impl.GridCrud;

public class BeverageCrudComponent extends GridCrud<Beverage> {
    public BeverageCrudComponent(CrudListener<Beverage> listener) {
        super(Beverage.class, listener);
        getGrid().setColumns("name", "alcoholic", "diet", "priceInHuf", "volumeInMililiters");
        getCrudFormFactory().setVisibleProperties("name", "alcoholic", "diet", "priceInHuf", "volumeInMililiters");
    }
}
