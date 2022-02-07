package com.example.demo.ui.enduser.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("enduser")
public class UserView extends VerticalLayout {
    public UserView() {
        add(
                new H1("User View")
        );
    }
}
