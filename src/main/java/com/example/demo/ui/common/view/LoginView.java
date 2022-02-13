package com.example.demo.ui.common.view;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

@Route("app/public/login")
public class LoginView extends Composite<LoginOverlay> {
    public LoginView() {
        getContent().setOpened(true);
        getContent().setAction("login");
        getContent().setTitle("Jean-Philippe");
        getContent().setDescription("Jean-Philippe is a serving processing application.");


        getContent().addForgotPasswordListener(forgotPasswordEvent -> Notification.show("Too bad buddy.", 5000, Notification.Position.BOTTOM_CENTER));
    }
}

//@Route("login")
//public class LoginView extends VerticalLayout {
//    public LoginView() {
//        var loginForm = new LoginForm();
//        loginForm.setAction("login");
//        add(loginForm);
//        add(new Hr());
//        add(new H1("Registration"));
//        setSpacing(true);
//        setAlignItems(Alignment.CENTER);
//    }
//}
