package com.ecommercebd.security;

import com.ecommercebd.user.domain.Role;
import com.ecommercebd.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email));

        final Role role = user.getRole();

        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());

        return new User(
                user.getEmail(),
                user.getPassword(),
                Arrays.asList(simpleGrantedAuthority)
        );
    }

}
