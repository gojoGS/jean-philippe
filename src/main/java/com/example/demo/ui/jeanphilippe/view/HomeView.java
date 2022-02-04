package com.example.demo.ui.jeanphilippe.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("home")
@AnonymousAllowed
public class HomeView extends VerticalLayout {
    public HomeView() {
        add(
                new H1("Jean-Philippe welcomes you")
        );
    }
}
