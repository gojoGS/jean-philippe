package com.example.demo.ui.enduser.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("app/end-user/ready")
public class OrderReadyScreen extends EndUserViewBase{

    public OrderReadyScreen() {
        super("Order ready");

        var newOrderButton = new Button("New order", buttonClickEvent -> UI.getCurrent().navigate(OrderView.class));
        var payButton = new Button("Check out", buttonClickEvent -> UI.getCurrent().navigate(CheckOutView.class));

        add(
                new H1("Your order is ready"),
                new H2("Enjoy your meal! You may record an other order or may check out!"),
                new HorizontalLayout(
                        newOrderButton, payButton
                )
        );
    }
}
