package com.example.demo.ui.enduser.view;

import com.example.demo.security.user.enduser.service.details.EndUserDetailsService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Route("app/end-user/waiting")
@Slf4j
public class InTheMakingView extends EndUserViewBase {
    @Autowired
    public InTheMakingView(EndUserDetailsService endUserDetailsService) {
        super("In the making");
        log.info("after super init");
        var order = endUserDetailsService.getUser().getTable().getOrderSession().getLastOrder();

        add(
                new H1("Lili süti a tortát"),
                new H1("Your order is in the making"),
                new H2(String.format("In about %d minutes our college, %s, will serve you your meal", order.getEstimatedTimeOfDoingInMinutes(), order.getServer().getName()))
        );
    }
}
