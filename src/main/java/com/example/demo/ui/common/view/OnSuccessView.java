package com.example.demo.ui.common.view;

import com.example.demo.ui.jeanphilippe.view.HomeView;
import com.example.demo.ui.restaurant.view.RestaurantHomeView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("/onSuccess")
public class OnSuccessView extends HorizontalLayout implements BeforeEnterObserver {

    public OnSuccessView() {
        add(
                new H1("nooooo")
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        var authorities = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();


        if (authorities.contains("ROLE_RESTAURANT")) {
            beforeEnterEvent.rerouteTo(RestaurantHomeView.class);
        } else {
            beforeEnterEvent.rerouteTo(HomeView.class);
        }
    }
}
