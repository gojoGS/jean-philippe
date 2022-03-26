package com.example.demo.ui.restaurant.component;


import com.example.demo.backend.item.core.Item;
import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.server.core.Server;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class OrderDialog extends Dialog {
    public OrderDialog(Order order) {
        var items = new Grid<Item>(Item.class, false);
        items.addColumn(Item::getName);

        items.setItems(order.getItems());

        var servers = order
                .getOrderSession()
                .getRestaurantTable()
                .getRestaurant()
                .getServers()
                .toArray(new Server[0]);


        var select = new Select<Server>(servers);
        select.setItemLabelGenerator(Server::getName);
        select.setLabel("Server");

        var input = new TextField();
        input.setLabel("Estimated time");

        var closeButton = new Button("Close");
        closeButton.addClickListener(buttonClickEvent -> close());

        var saveButton = new Button("Save");

        var buttons = new HorizontalLayout(
                closeButton,
                saveButton
        );

        var content = new VerticalLayout(
                items,
                select,
                input,
                buttons
        );

        add(
                content
        );
    }
}
