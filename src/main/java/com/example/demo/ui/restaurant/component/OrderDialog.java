package com.example.demo.ui.restaurant.component;


import com.example.demo.backend.broadcast.Broadcaster;
import com.example.demo.backend.broadcast.Event;
import com.example.demo.backend.broadcast.EventType;
import com.example.demo.backend.item.core.Item;
import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.order.core.OrderStatus;
import com.example.demo.backend.order.repository.OrderRepository;
import com.example.demo.backend.server.core.Server;
import com.example.demo.ui.util.NotificationUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;

public class OrderDialog extends Dialog {
    Select<Server> serverSelect = new Select<>();
    IntegerField estimateInput = new IntegerField("Estimated time");
    Order order;
    OrderRepository repository;

    public OrderDialog(Order order, OrderRepository repository) {
        var items = new Grid<Item>(Item.class, false);
        this.order = order;
        this.repository = repository;
        items.addColumn(Item::getName);

        items.setItems(order.getItems());

        var servers = order
                .getOrderSession()
                .getRestaurantTable()
                .getRestaurant()
                .getServers()
                .toArray(new Server[0]);

        serverSelect.setItems(servers);
        serverSelect.setItemLabelGenerator(Server::getName);
        serverSelect.setLabel("Server");

        Div minSuffix = new Div();
        minSuffix.setText("min(s)");

        estimateInput.setSuffixComponent(minSuffix);

        var closeButton = new Button("Close");
        closeButton.addClickListener(buttonClickEvent -> close());

        var saveButton = new Button("Save", buttonClickEvent -> save());

        var buttons = new HorizontalLayout(
                closeButton,
                saveButton
        );

        var content = new VerticalLayout(
                items,
                serverSelect,
                estimateInput,
                buttons
        );

        setWidth("80%");

        add(
                content
        );
    }

    private void save() {
        Server server = serverSelect.getValue();
        if(server == null) {
            NotificationUtil.showError("Select a server");
            return;
        }

        int estimate = estimateInput.getValue();

        if(estimate <= 0) {
            NotificationUtil.showError("Please provide a valid estimate");
            return;
        }

        this.order.setServer(server);
        this.order.setEstimatedTimeOfDoingInMinutes(estimate);
        this.order.setOrderStatus(OrderStatus.IN_PROGRESS);

        repository.save(order);
        Broadcaster.broadcast(
                new Event(
                        EventType.ORDER_ACCEPTED,
                    order.getOrderSession().getId()
                )
        );
        this.close();
        UI.getCurrent().getPage().reload();
    }
}
