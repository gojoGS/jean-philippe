package com.example.demo.ui.jeanphilippe.view;

import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/public/restaurants")
public class RestaurantsView extends JeanPhilippeViewBase {
    @Autowired
    public RestaurantsView(RestaurantRepository restaurantRepository) {
        super("Home");

        var grid = new Grid<>(Restaurant.class, false);
        grid.addColumn(Restaurant::getName).setHeader("Name");
        grid.addItemClickListener(restaurantItemClickEvent -> {
            var restaurant = restaurantItemClickEvent.getItem();

            var dialog = new Dialog();

            var button = new Button("Close");
            button.addClickListener(buttonClickEvent -> dialog.close());

            var content = new VerticalLayout(
                    new H1(restaurant.getName()),
                    new Text(restaurant.getDescription()),
                    button
            );

            content.setAlignItems(Alignment.CENTER);
            content.setJustifyContentMode(JustifyContentMode.BETWEEN);
            content.setSpacing(true);

            dialog.add(content);

            dialog.open();
        });

        grid.setItems(restaurantRepository.findAll());

        add(
                new H1("Restaurants"),
                grid
        );
    }
}
