package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.base.EntityServiceAdapter;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.backend.server.core.Server;
import com.example.demo.ui.restaurant.component.CrudComponent;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/staff")
public class RestaurantStaffView extends RestaurantViewBase {
    private final String[] crudProperties = {"name"};

    @Autowired
    public RestaurantStaffView(EntityServiceFactory<Server> serverEntityServiceFactory) {
        super("Staff", "Staff");

        add(
                new CrudComponent<>(
                        new EntityServiceAdapter<>(serverEntityServiceFactory.get(restaurantId)),
                        Server.class,
                        crudProperties
                )
        );
    }
}
