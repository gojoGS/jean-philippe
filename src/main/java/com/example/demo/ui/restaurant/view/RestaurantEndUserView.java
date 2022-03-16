package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.restaurant.service.base.EntityService;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.backend.table.core.RestaurantTable;
import com.example.demo.security.service.password.generation.PasswordGenerationService;
import com.example.demo.ui.util.NotificationUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route("app/restaurant/enduser")
public class RestaurantEndUserView extends RestaurantViewBase{
    private final EntityService<RestaurantTable> restaurantTableEntityService;

    @Autowired
    PasswordGenerationService passwordGenerationService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public RestaurantEndUserView(EntityServiceFactory<RestaurantTable> tableEntityServiceFactory) {
        super("Endusers", "Endusers");
        this.restaurantTableEntityService = tableEntityServiceFactory.get(this.restaurantId);

        var grid = new Grid<>(RestaurantTable.class, false);

        grid.addColumn(restaurantTable -> String.format("Table #%d", restaurantTable.getNumber())).setHeader("Table number");
        grid.addColumn(restaurantTable -> restaurantTable.getUser().getUserId()).setHeader("UserID");


        grid.addComponentColumn(restaurantTable -> {
            var button = new Button("New password");

            button.addClickListener(buttonClickEvent -> {
                var newPassword = passwordGenerationService.get();
                var user = restaurantTable.getUser();
                user.setEncryptedPassword(passwordEncoder.encode(newPassword));

                restaurantTable.setUser(user);
                restaurantTableEntityService.update(restaurantTable);

                new Dialog(
                        new H1(String.format("New password is: %s", newPassword))
                ).open();
            });
            return button;
        }).setHeader("Password");

        grid.setItems(restaurantTableEntityService.getAll());

        add(grid);
    }

    private void onNewPassword() {

    }
}
