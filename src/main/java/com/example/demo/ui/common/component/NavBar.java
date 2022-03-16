package com.example.demo.ui.common.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@CssImport("./styles/styles.css")
public class NavBar extends HorizontalLayout {
    public NavBar(Optional<String> label, NavOption... options) {
        getClassNames().add("nav-bar");

        // TOPIC functional style expression
        label.ifPresent(s -> add(
                new H1(s)
        ));

        for (var option : options) {
            var navButton = new Button(option.getLabel());
            navButton.setThemeName("primary");
            navButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(option.getRoute()));

            add(navButton);
            setWidthFull();
            setAlignItems(Alignment.CENTER);
            setJustifyContentMode(JustifyContentMode.AROUND);
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class NavOption {
        private String label;
        private Class<? extends Component> route;
    }
}