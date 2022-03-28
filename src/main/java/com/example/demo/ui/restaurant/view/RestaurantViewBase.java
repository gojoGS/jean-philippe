package com.example.demo.ui.restaurant.view;

import com.example.demo.backend.broadcast.Broadcaster;
import com.example.demo.backend.broadcast.EventType;
import com.example.demo.backend.broadcast.ReadyToCheckoutEvent;
import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.backend.session.core.OrderSession;
import com.example.demo.backend.table.core.RestaurantTable;
import com.example.demo.security.user.restaurant.service.details.RestaurantAuthDetailsService;
import com.example.demo.ui.common.component.nav.NavBar;
import com.example.demo.ui.common.view.ViewBase;
import com.example.demo.ui.enduser.view.InTheMakingView;
import com.example.demo.ui.util.BeanUtil;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


// TOPIC abstract class
@Slf4j
public abstract class RestaurantViewBase extends ViewBase {
    // TOPIC why private

    protected final String restaurantName;
    protected final long restaurantId;
    protected Restaurant restaurant;
    Registration broadcasterRegistration;


    // TOPIC subclass API
    protected RestaurantViewBase(String title, String header) {
        super(title);
        RestaurantAuthDetailsService detailsService = BeanUtil.getBean(RestaurantAuthDetailsService.class);

        this.restaurantId = detailsService.getUser().getRestaurant().getId();
        this.restaurantName = detailsService.getUser().getRestaurant().getName();
        this.restaurant = detailsService.getUser().getRestaurant();

        var navBar = new NavBar(
                Optional.of(restaurantName),
                NavBar.NavOption.builder().label("Profile").route(RestaurantProfileView.class).build(),
                NavBar.NavOption.builder().label("Menu").route(RestaurantMenuView.class).build(),
                NavBar.NavOption.builder().label("Tables").route(RestaurantTablesView.class).build(),
                NavBar.NavOption.builder().label("End user").route(RestaurantEndUserView.class).build(),
                NavBar.NavOption.builder().label("Staff").route(RestaurantStaffView.class).build(),
                NavBar.NavOption.builder().label("Orders").route(RestaurantOrdersView.class).build()
        );

        add(navBar);
        add(new H1(header));
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI ui = attachEvent.getUI();
        broadcasterRegistration = Broadcaster.register(event -> {
            log.info("got event");
            log.info(event.getType().name());

            if(event.getType() == EventType.ORDER_NEW) {
                if (
                        restaurant.getRestaurantTables()
                                .stream()
                                .map(RestaurantTable::getOrderSession)
                                .map(OrderSession::getId)
                                .noneMatch(id -> id.equals(event.getSessionId()))
                ) {
                    log.info("wasnt mine");
                    return;
                }

                ui.access(() -> {
                    Notification notification = new Notification();
                    notification.setPosition(Notification.Position.BOTTOM_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);

                    Div text = new Div(new Text("New order"));

                    Button closeButton = new Button(new Icon("lumo", "cross"));
                    closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
                    closeButton.getElement().setAttribute("aria-label", "Close");
                    closeButton.addClickListener(_event-> {
                        notification.close();
                    });

                    HorizontalLayout layout = new HorizontalLayout(text, closeButton);
                    layout.setAlignItems(Alignment.CENTER);

                    notification.add(layout);
                    notification.open();
                });
            }

            if(event.getType() == EventType.READY_TO_CHECKOUT) {
                var cEvent = (ReadyToCheckoutEvent) event;
                if (
                        restaurant.getRestaurantTables()
                                .stream()
                                .map(RestaurantTable::getId)
                                .noneMatch(id -> id.equals(cEvent.getTableId()))
                ) {
                    log.info("wasnt mine");
                    return;
                }


                ui.access(() -> {
                    Notification notification = new Notification();
                    notification.setPosition(Notification.Position.BOTTOM_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);

                    Div text = new Div(new Text(String.format("Table %d is ready to pay, by %s", cEvent.getTableNumber(), cEvent.getPaymentMethod().getName())));

                    Button closeButton = new Button(new Icon("lumo", "cross"));
                    closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
                    closeButton.getElement().setAttribute("aria-label", "Close");
                    closeButton.addClickListener(_event-> {
                        notification.close();
                    });

                    HorizontalLayout layout = new HorizontalLayout(text, closeButton);
                    layout.setAlignItems(Alignment.CENTER);

                    notification.add(layout);
                    notification.open();
                });
            }
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        broadcasterRegistration.remove();
        broadcasterRegistration = null;
    }
}