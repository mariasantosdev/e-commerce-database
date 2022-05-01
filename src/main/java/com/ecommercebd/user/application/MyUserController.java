package com.ecommercebd.user.application;

import com.ecommercebd.exception.NotFoundException;
import com.ecommercebd.mapper.Mapper;
import com.ecommercebd.security.IsClientOrAdmin;
import com.ecommercebd.user.domain.Role;
import com.ecommercebd.user.domain.User;
import com.ecommercebd.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MyUserController {

    private final Mapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @IsClientOrAdmin
    @GetMapping("user/me")
    UserResponse profile(@AuthenticationPrincipal UserDetails userDetails) {
       return userRepository.findByEmail(userDetails.getUsername())
               .map(p -> mapper.map(p, UserResponse.class))
               .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    @IsClientOrAdmin
    @PutMapping("user/update-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updatePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateUserPasswordRequest request) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse create(@RequestBody @Valid NewPublicUserRequest request){
        User user = mapper.map(request, User.class);
        user.setRole(Role.CLIENT);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = this.userRepository.save(user);

        return this.mapper.map(user, UserResponse.class);
    }

}
