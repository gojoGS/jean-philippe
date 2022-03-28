package com.example.demo.ui.enduser.view;

import com.example.demo.backend.broadcast.Broadcaster;
import com.example.demo.backend.broadcast.EventType;
import com.example.demo.security.user.enduser.service.details.EndUserDetailsService;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Route("app/end-user/sent")
public class SentToRestaurantView extends EndUserViewBase {
    Registration broadcasterRegistration;
    long sessionId;

    @Autowired
    public SentToRestaurantView(EndUserDetailsService endUserDetailsService) {
        super("Sent to restaurant");

        this.sessionId = endUserDetailsService.getUser().getTable().getOrderSession().getId();

        add(
                new H1("Your order has been sent"),
                new H2("The restaurant has received your order. They will respond with a time estimate in a few minutes.")
        );
    }



    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI ui = attachEvent.getUI();
        broadcasterRegistration = Broadcaster.register(event -> {
            if (event.getSessionId() != this.sessionId) {
                return;
            }

            if(event.getType() != EventType.ORDER_ACCEPTED) {
                return;
            }

            ui.access(() -> {
                add(
                        new H1("The staff has updated your order with an estimate. Proceed to view."),
                        new Button("Proceed", buttonClickEvent -> UI.getCurrent().navigate(InTheMakingView.class))
                );
            });
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        broadcasterRegistration.remove();
        broadcasterRegistration = null;
    }
}
