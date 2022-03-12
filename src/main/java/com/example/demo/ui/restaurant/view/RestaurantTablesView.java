package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.base.EntityServiceAdapter;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.backend.table.core.Table;
import com.example.demo.ui.restaurant.component.TableCrudComponent;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/tables")
public class RestaurantTablesView extends RestaurantViewBase {
    @Autowired
    public RestaurantTablesView(EntityServiceFactory<Table> tableCrudServiceFactory) {
        super("Tables", "Tables");

        add(
                new TableCrudComponent(
                        new EntityServiceAdapter<>(tableCrudServiceFactory.get(restaurantId))
                )
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
