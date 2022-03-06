package com.example.demo.backend.restaurant.service.crud.beverage;

import com.example.demo.backend.beverage.core.Beverage;
import org.vaadin.crudui.crud.CrudListener;

public interface BeverageCrudServiceFactory {
    CrudListener<Beverage> get(long restaurantId);
}
