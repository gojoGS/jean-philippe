package com.example.demo.ui.enduser.component;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Header extends HorizontalLayout {
    public Header(String label) {
        getClassNames().add("nav-bar");

        add(
                new H1(label)
        );

        setWidthFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
    }
}
