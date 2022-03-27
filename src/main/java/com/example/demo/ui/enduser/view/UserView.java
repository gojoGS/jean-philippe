package com.example.demo.ui.enduser.view;

import com.example.demo.backend.order.core.OrderStatus;
import com.example.demo.backend.session.core.OrderSessionStatus;
import com.example.demo.security.user.enduser.service.details.EndUserDetailsService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/end-user/")
@Slf4j
public class UserView extends VerticalLayout implements BeforeEnterObserver {
    private OrderSessionStatus orderStatus;

    @Autowired
    public UserView(EndUserDetailsService endUserDetailsService) {
        var status = endUserDetailsService.getUser().getTable().getOrderSession().getSessionStatus();
        this.orderStatus = status;
        log.info(String.format("Order status is: %s", status.getStatus()));

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        switch(this.orderStatus) {
            case CAN_ORDER -> beforeEnterEvent.rerouteTo(StartView.class);
            case SENT_TO_RESTAURANT -> beforeEnterEvent.rerouteTo(SentToRestaurantView.class);
            case IN_THE_MAKING -> beforeEnterEvent.rerouteTo(InTheMakingView.class);
        }
    }
}
