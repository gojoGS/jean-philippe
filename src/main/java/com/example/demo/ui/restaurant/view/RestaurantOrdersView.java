package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.order.core.OrderStatus;
import com.example.demo.backend.order.repository.OrderRepository;
import com.example.demo.backend.restaurant.service.order.OrderServiceFactory;
import com.example.demo.ui.restaurant.component.OrderDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tabs.PagedTabs;

@Route("app/restaurant/orders")
public class RestaurantOrdersView extends RestaurantViewBase {
    private static final OrderStatus defaultStatus = OrderStatus.WAITING;

    @Autowired
    public RestaurantOrdersView(OrderServiceFactory orderServiceFactory, OrderRepository orderRepository) {
        super("Orders", "Orders");

        var ordersService = orderServiceFactory.get(this.restaurantId);
        var ordersGrid = new Grid<>(Order.class, false);

        ordersGrid.addColumn(order -> order.getOrderSession().getRestaurantTable().getNumber()).setHeader("Table number");
        ordersGrid.addColumn(order -> order.getOrderStatus().getStatus()).setHeader("Status");

        ordersGrid.addItemClickListener(orderItemClickEvent -> new OrderDialog(orderItemClickEvent.getItem(), orderRepository).open());
        ordersGrid.setItems(ordersService.getOrdersByStatus(defaultStatus));

        add(
                ordersGrid
        );
    }
}
