package com.example.demo.backend.restaurant.service.crud.beverage;

import com.example.demo.backend.beverage.core.Beverage;
import com.example.demo.backend.restaurant.service.beverage.BeverageService;
import com.example.demo.backend.restaurant.service.beverage.BeverageServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Component
public class BeverageCrudServiceFactoryImpl implements BeverageCrudServiceFactory{
    private final BeverageServiceFactory beverageServiceFactory;

    @Autowired
    public BeverageCrudServiceFactoryImpl(BeverageServiceFactory beverageServiceFactory) {
        this.beverageServiceFactory = beverageServiceFactory;
    }

    @Override
    public CrudListener<Beverage> get(long restaurantId) {
        return new BeverageCrudServiceImpl(restaurantId);
    }

    private class BeverageCrudServiceImpl implements CrudListener<Beverage> {
        private final BeverageService beverageService;

        public BeverageCrudServiceImpl(long restaurantId) {
            this.beverageService = beverageServiceFactory.get(restaurantId);
        }

        @Override
        public Collection<Beverage> findAll() {
            return beverageService.getAll();
        }

        @Override
        public Beverage add(Beverage beverage) {
            beverageService.addBeverage(beverage);
            return beverage;
        }

        @Override
        public Beverage update(Beverage beverage) {
            beverageService.update(beverage);
            return beverage;
        }

        @Override
        public void delete(Beverage beverage) {
            beverageService.removeBeverage(beverage);
        }
    }
}
