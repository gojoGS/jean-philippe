package com.example.demo.backend.session.service;

import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.session.core.OrderSession;
import com.example.demo.backend.session.repository.OrderSessionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SessionServiceFactoryImpl implements SessionServiceFactory {
    @Autowired
    OrderSessionRepository orderSessionRepository;

    @Override
    public SessionService get(long sessionId) {
        return new SessionServiceImpl(sessionId);
    }

    @AllArgsConstructor
    private class SessionServiceImpl implements SessionService {
        private long sessionId;

        private OrderSession getOrderSession() {
            var session = orderSessionRepository.findById(sessionId);

            if(session.isEmpty()) {
                log.error("session was not found");
                throw new RuntimeException("fucked up session");
            }

            return session.get();
        }

        @Override
        public void addOrder(Order order) {
            var session = getOrderSession();
            session.addOrder(order);
            orderSessionRepository.save(session);
        }
    }
}
