package com.ecommercebd.user.application;

import com.ecommercebd.exception.NotFoundException;
import com.ecommercebd.mapper.Mapper;
import com.ecommercebd.security.IsClientOrAdmin;
import com.ecommercebd.user.domain.User;
import com.ecommercebd.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@IsClientOrAdmin
@RestController
@RequiredArgsConstructor
public class MyUserController {

    private final Mapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("user/me")
    UserResponse profile(@AuthenticationPrincipal UserDetails userDetails) {
       return userRepository.findByEmail(userDetails.getUsername())
               .map(p -> mapper.map(p, UserResponse.class))
               .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    @PutMapping("user/update-password")
    void updatePassword(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

    }
}
