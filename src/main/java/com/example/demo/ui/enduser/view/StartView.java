package com.example.demo.ui.enduser.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("app/end-user/start")
public class StartView extends VerticalLayout {
    public StartView() {
        var startButton = new Button("Start session");
        startButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(OrderView.class));

        add(
            startButton
        );

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
