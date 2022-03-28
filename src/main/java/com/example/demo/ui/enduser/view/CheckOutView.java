package com.example.demo.ui.enduser.view;

import com.example.demo.backend.broadcast.Broadcaster;
import com.example.demo.backend.broadcast.Event;
import com.example.demo.backend.broadcast.EventType;
import com.example.demo.backend.broadcast.ReadyToCheckoutEvent;
import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.payment.PaymentMethod;
import com.example.demo.backend.session.core.OrderSession;
import com.example.demo.backend.table.repository.RestaurantTableRepository;
import com.example.demo.security.user.enduser.service.details.EndUserDetailsService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Route("app/end-user/checkout")

public class CheckOutView extends EndUserViewBase{
    @Autowired
    public CheckOutView(EndUserDetailsService endUserDetailsService, RestaurantTableRepository repository) {
        super("Check out");

        long total = endUserDetailsService
                .getUser()
                .getTable()
                .getOrderSession()
                .getOrders()
                .stream()
                .map(Order::getTotalPrice)
                .reduce(0L, Long::sum);

        var select = new Select<>(
                PaymentMethod.values()
        );

        add(
                new H1(String.format("Your total is: %d", total)),
                select,
                new Button("Check out", buttonClickEvent -> {
                    var table = endUserDetailsService.getUser().getTable();
                    var newSession = new OrderSession();
                    newSession.setRestaurantTable(table);

                    table.setOrderSession(newSession);
                    repository.save(table);

                    Broadcaster.broadcast(
                            new ReadyToCheckoutEvent(
                                    EventType.READY_TO_CHECKOUT,
                                    0L,
                                    table.getId(),
                                    table.getNumber(),
                                    select.getValue()
                            )
                    );

                    UI.getCurrent().navigate(StartView.class);
                })
        );
    }
}
