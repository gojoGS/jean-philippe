package com.example.demo.ui.enduser.view;

import com.example.demo.security.user.enduser.service.details.EndUserDetailsService;
import com.example.demo.ui.common.view.ViewBase;
import com.example.demo.ui.enduser.component.Header;
import com.example.demo.ui.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class EndUserViewBase extends ViewBase {
    protected EndUserViewBase(String title) {
        super(title);
        log.info(title);
        log.info("before details service");
        var detailsService = BeanUtil.getBean(EndUserDetailsService.class);
        log.info("after details service");

        add(
                new Header(String.format(
                        "%s | %s",
                        detailsService.getUser().getTable().getRestaurant().getName(),
                        title))
        );

        log.info("after details service call");
    }
}
