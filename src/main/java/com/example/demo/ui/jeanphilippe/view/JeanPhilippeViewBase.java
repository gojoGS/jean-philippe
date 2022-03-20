package com.example.demo.ui.jeanphilippe.view;

import com.example.demo.ui.common.component.nav.NavBar;
import com.example.demo.ui.common.component.nav.NavBarBuilderImpl;
import com.example.demo.ui.common.view.ViewBase;

public abstract class JeanPhilippeViewBase extends ViewBase {
    protected JeanPhilippeViewBase(String title) {
        super(title);

        var navBar = new NavBarBuilderImpl()
                .setLabel("Jean Philippe")
                .addOption(new NavBar.NavOption("Home", HomeView.class))
                .addOption(new NavBar.NavOption("Register restaurant", RestaurantSignupView.class))
                .addOption(new NavBar.NavOption("Restaurants", RestaurantsView.class))
                .addOption(new NavBar.NavOption("Search", SearchView.class))
                .build();

        add(
                navBar
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
