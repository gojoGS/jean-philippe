package com.example.demo.ui.enduser.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("app/end-user/sent")
public class SentToRestaurantView extends VerticalLayout {
    public SentToRestaurantView() {
        add(
                new H1("Your order has been sent")
        );
    }
}
