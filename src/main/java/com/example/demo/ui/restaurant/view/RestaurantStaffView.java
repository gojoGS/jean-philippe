package com.example.demo.ui.restaurant.view;

import com.example.demo.security.user.restaurant.service.RestaurantUserService;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Route("app/restaurant/staff")
@Slf4j
public class RestaurantStaffView extends RestaurantViewBase {
    @Autowired
    public RestaurantStaffView(RestaurantUserService userService) {
        super("Staff", "Staff");
        setRestaurantId(userService);
    }

    protected void setRestaurantId(RestaurantUserService userService) {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = "";

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        }

        var user =
                userService.getUser(email);

        if (!user.isPresent()) {
            log.error("fuck up m8");
        } else {
            this.restaurantId = user.get().getRestaurant().getId();
        }

        log.info(String.format("restaurantId is %d", restaurantId));
    }
}
