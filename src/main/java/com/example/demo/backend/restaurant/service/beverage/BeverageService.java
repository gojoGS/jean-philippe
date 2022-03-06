package com.example.demo.backend.restaurant.service.beverage;

import com.example.demo.backend.beverage.core.Beverage;

import java.util.Collection;

public interface BeverageService {
    void addBeverage(Beverage beverage);

    Collection<Beverage> getAll();

    void update(Beverage beverage);

    void removeBeverage(Beverage beverage);
}
