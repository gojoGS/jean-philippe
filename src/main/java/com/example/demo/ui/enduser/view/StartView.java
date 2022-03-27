package com.example.demo.ui.enduser.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;

@Route("app/end-user/start")
public class StartView extends EndUserViewBase {
    public StartView() {
        super("Start session");

        var startButton = new Button("Start session");
        startButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(OrderView.class));

        add(
            startButton
        );
    }
}
