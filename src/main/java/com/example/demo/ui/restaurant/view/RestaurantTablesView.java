package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.crud.table.TableCrudServiceFactory;
import com.example.demo.ui.restaurant.component.TableCrudComponent;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/tables")
public class RestaurantTablesView extends RestaurantViewBase {
    @Autowired
    public RestaurantTablesView(TableCrudServiceFactory tableCrudServiceFactory) {
        super("Tables", "Tables");

        add(
                new TableCrudComponent(tableCrudServiceFactory.get(restaurantId))
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
