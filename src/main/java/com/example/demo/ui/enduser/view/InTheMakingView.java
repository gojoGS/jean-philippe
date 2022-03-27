package com.example.demo.ui.enduser.view;

import com.example.demo.backend.broadcast.Broadcaster;
import com.example.demo.backend.broadcast.EventType;
import com.example.demo.security.user.enduser.service.details.EndUserDetailsService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/end-user/waiting")
@Slf4j
public class InTheMakingView extends EndUserViewBase {
    Registration broadcasterRegistration;
    long sessionId;

    @Autowired
    public InTheMakingView(EndUserDetailsService endUserDetailsService) {
        super("In the making");
        log.info("after super init");
        var order = endUserDetailsService.getUser().getTable().getOrderSession().getLastOrder();
        this.sessionId = endUserDetailsService.getUser().getTable().getOrderSession().getId();


        add(
                new H1("Lili süti a tortát"),
                new H1("Your order is in the making"),
                new H2(String.format("In about %d minutes our college, %s, will serve you your meal", order.getEstimatedTimeOfDoingInMinutes(), order.getServer().getName()))
        );
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI ui = attachEvent.getUI();
        broadcasterRegistration = Broadcaster.register(event -> {
            if(event.getType() != EventType.ORDER_READY) {
                return;
            }

            if (event.getSessionId() != this.sessionId) {
                return;
            }

            ui.access(() -> {
                ui.navigate(OrderReadyScreen.class);
            });
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        broadcasterRegistration.remove();
        broadcasterRegistration = null;
    }
}
