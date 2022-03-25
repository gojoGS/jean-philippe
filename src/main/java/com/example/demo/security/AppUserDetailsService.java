package com.example.demo.security;

import com.example.demo.security.user.enduser.repository.EndUserRepository;
import com.example.demo.security.user.restaurant.repository.RestaurantUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    RestaurantUserRepository restaurantUserRepository;

    @Autowired
    EndUserRepository enduserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var result = restaurantUserRepository.getByEmail(username);

        if(result.isPresent()) {
            var user = result.get();
            return new User(user.getEmail(), user.getEncryptedPassword(), getAuthorities(List.of("ROLE_RESTAURANT")));
        }

        var result2 = enduserRepository.getEndUserByUserId(username);

        if(result2.isPresent()) {
            var user = result2.get();
            log.info(String.format("username is %s password is %s", user.getUserId(), user.getEncryptedPassword()));
            return new User(user.getUserId(), user.getEncryptedPassword(), getAuthorities(List.of("ROLE_END_USER")));
        }

        throw new UsernameNotFoundException("No user found with username: " + username);
    }

    private static List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
