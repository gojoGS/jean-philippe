package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.base.EntityServiceAdapter;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.backend.table.core.RestaurantTable;
import com.example.demo.ui.restaurant.component.CrudComponent;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/tables")
public class RestaurantTablesView extends RestaurantViewBase {
    private final String[] crudProperties = {"number", "size", "description"};

    @Autowired
    public RestaurantTablesView(EntityServiceFactory<RestaurantTable> tableEntityServiceFactory) {
        super("Tables", "Tables");

        add(
                new CrudComponent<>(
                        new EntityServiceAdapter<>(tableEntityServiceFactory.get(restaurantId)),
                        RestaurantTable.class,
                        crudProperties
                )
        );


    }
}
