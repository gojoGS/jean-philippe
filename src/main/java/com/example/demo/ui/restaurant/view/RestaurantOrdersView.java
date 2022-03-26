package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.order.core.OrderStatus;
import com.example.demo.backend.restaurant.service.order.OrderServiceFactory;
import com.example.demo.ui.restaurant.component.OrderDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/restaurant/orders")
public class RestaurantOrdersView extends RestaurantViewBase {
    @Autowired
    public RestaurantOrdersView(OrderServiceFactory orderServiceFactory) {
        super("Orders", "Orders");

        var ordersService = orderServiceFactory.get(this.restaurantId);
        var ordersGrid = new Grid<Order>(Order.class, false);

        ordersGrid.addColumn(order -> order.getOrderSession().getRestaurantTable().getNumber()).setHeader("Table number");

        ordersGrid.addItemClickListener(orderItemClickEvent -> new OrderDialog(orderItemClickEvent.getItem()).open());
        ordersGrid.setItems(ordersService.getOrdersByStatus(OrderStatus.WAITING));

        add(ordersGrid);

    }
}
