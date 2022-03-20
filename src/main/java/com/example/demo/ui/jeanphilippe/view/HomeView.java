package com.example.demo.ui.jeanphilippe.view;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route("app/public/home")
@RouteAlias("/")
public class HomeView extends JeanPhilippeViewBase {
    public HomeView() {
        super("Home");
    }
}
