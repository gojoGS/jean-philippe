package com.example.demo.ui.enduser.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route("enduser")
@RolesAllowed("USER")
public class UserView extends VerticalLayout {
    public UserView() {
        add(
                new H1("User View")
        );
    }
}
