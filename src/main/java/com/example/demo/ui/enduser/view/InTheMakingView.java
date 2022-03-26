package com.example.demo.ui.enduser.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("app/end-user/waiting")
public class InTheMakingView extends VerticalLayout {
    public InTheMakingView() {
        add(
                new H1("Lili süti a tortát")
        );
    }
}
