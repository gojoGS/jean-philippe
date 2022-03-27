package com.example.demo.ui.common.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HasDynamicTitle;
import lombok.AllArgsConstructor;
import lombok.Setter;

@CssImport("./styles/styles.css")
public abstract class ViewBase extends VerticalLayout implements HasDynamicTitle {
    @Setter
    protected String title;

    protected ViewBase(String title) {
        setPadding(false);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        this.title = title;
    }

    @Override
    public String getPageTitle() {
        // TOPIC String.format performance benchmark study
        return String.format("JP | %s", title);
    }
}
