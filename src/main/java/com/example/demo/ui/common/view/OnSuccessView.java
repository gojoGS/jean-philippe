package com.example.demo.ui.common.view;

import com.example.demo.ui.admin.view.AdminView;
import com.example.demo.ui.enduser.view.UserView;
import com.example.demo.ui.jeanphilippe.view.HomeView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Route("")
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

        System.out.println(String.format("Authorities: %s", authorities));

        if (authorities.contains("ROLE_ADMIN")) {
            beforeEnterEvent.rerouteTo(AdminView.class);
            System.out.println("admin");
        } else if (authorities.contains("ROLE_USER")) {
            System.out.println("enduser");
            beforeEnterEvent.rerouteTo(UserView.class);
        } else {
            System.out.println("anon");
            beforeEnterEvent.rerouteTo(HomeView.class);
        }
    }
}
