package com.example.demo.ui.jeanphilippe.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route("app/public/home")
@RouteAlias("/")
public class HomeView extends JeanPhilippeViewBase {
    public HomeView() {
        super("Home");

        add(
                new H1("Jean-Philippe"),
                new H2("The web-based ordering application, for restaurants"),
                new H3("""
                        Jean-Philippe provides a clean, intuitive interface for restaurants, to manage their orders. It provides a smooth experience for restaurants and end-users alike.
                        """),
                new H3("A unified, web-based application provides the flexibility and light weighted approach of a website, with the feature-richness of a desktop application."),
                new H3("If you are interested using our service, please consider signing up your business. If you are interested in dining, please consider using our search tools.")

        );


    }
}
