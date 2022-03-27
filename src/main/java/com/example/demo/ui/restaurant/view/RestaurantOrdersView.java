package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.broadcast.Broadcaster;
import com.example.demo.backend.broadcast.Event;
import com.example.demo.backend.broadcast.EventType;
import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.order.core.OrderStatus;
import com.example.demo.backend.order.repository.OrderRepository;
import com.example.demo.backend.restaurant.service.order.OrderServiceFactory;
import com.example.demo.ui.restaurant.component.OrderDialog;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tabs.PagedTabs;

@Route("app/restaurant/orders")
public class RestaurantOrdersView extends RestaurantViewBase {
    private final OrderRepository orderRepository;

    @Autowired
    public RestaurantOrdersView(OrderServiceFactory orderServiceFactory, OrderRepository orderRepository) {
        super("Orders", "Orders");

        var ordersService = orderServiceFactory.get(this.restaurantId);
        this.orderRepository = orderRepository;
        var container = new VerticalLayout();
        var tabs = new PagedTabs(container);

        var waitingGrid = new Grid<>(Order.class, false);

        waitingGrid.addColumn(order -> order.getOrderSession().getRestaurantTable().getNumber()).setHeader("Table number");
        waitingGrid.addColumn(order -> order.getOrderStatus().getStatus()).setHeader("Status");

        waitingGrid.addItemClickListener(orderItemClickEvent -> new OrderDialog(orderItemClickEvent.getItem(), orderRepository).open());
        waitingGrid.setItems(ordersService.getOrdersByStatus(OrderStatus.WAITING));

        tabs.add(OrderStatus.WAITING.getStatus(), waitingGrid, false);

        var inProgressGrid = new Grid<>(Order.class, false);

        inProgressGrid.addColumn(order -> order.getOrderSession().getRestaurantTable().getNumber()).setHeader("Table number");
        inProgressGrid.addColumn(order -> order.getOrderStatus().getStatus()).setHeader("Status");
        inProgressGrid.addComponentColumn(order -> new Button("Close", buttonClickEvent -> closeOrder(order))).setHeader("Close order");
        inProgressGrid.setItems(ordersService.getOrdersByStatus(OrderStatus.IN_PROGRESS));

        tabs.add(OrderStatus.IN_PROGRESS.getStatus(), inProgressGrid, false);

        add(
                tabs,
                container
        );
    }

    private void closeOrder(Order order) {
        order.setOrderStatus(OrderStatus.CLOSED);
        orderRepository.save(order);
        Broadcaster.broadcast(new Event(EventType.ORDER_READY, order.getOrderSession().getId()));
        UI.getCurrent().getPage().reload();
    }
}
