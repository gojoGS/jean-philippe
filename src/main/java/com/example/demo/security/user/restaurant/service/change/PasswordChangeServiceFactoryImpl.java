package com.example.demo.security.user.restaurant.service.change;

import com.example.demo.security.user.restaurant.repository.RestaurantUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PasswordChangeServiceFactoryImpl implements  PasswordChangeServiceFactory {

    @Autowired
    RestaurantUserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public PasswordChangeService get(long userId) {
        return new PasswordChangeServiceImpl(userId);
    }

    @AllArgsConstructor
    private class PasswordChangeServiceImpl implements PasswordChangeService {

        private final long userId;

        @Override
        public void changePassword(String password) {
            var result  = userRepository.findById(userId);

            if(result.isEmpty()) {
                log.error(String.format("User with id %d was not found", userId));
                throw new RuntimeException(String.format("User with id %d was not found", userId));
            }

            var user = result.get();

            user.setEncryptedPassword(encoder.encode(password));

            userRepository.save(user);
        }

        @Override
        public boolean isCurrentPasswordConfirmationValid(String providedCurrentPassword) {
            var result  = userRepository.findById(userId);

            if(result.isEmpty()) {
                log.error(String.format("User with id %d was not found", userId));
                throw new RuntimeException(String.format("User with id %d was not found", userId));
            }

            var currentEncryptedPassword = result.get().getEncryptedPassword();

            return encoder.matches(providedCurrentPassword, currentEncryptedPassword);
        }
    }
}
