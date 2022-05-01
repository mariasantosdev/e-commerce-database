package com.ecommercebd.user.application;


import com.ecommercebd.exception.NotFoundException;
import com.ecommercebd.mapper.Mapper;
import com.ecommercebd.security.IsAdmin;
import com.ecommercebd.user.domain.User;
import com.ecommercebd.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@IsAdmin
class UserController {

    private final Mapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    List<UserResponse> findAll(){
        return userRepository.findAll()
                .stream()
                .map(p -> mapper.map(p, UserResponse.class)).toList();
    }

    @GetMapping("{userId}")
    UserResponse findById(@PathVariable Long userId){
        return userRepository.findById(userId)
                .map(p -> mapper.map(p, UserResponse.class))
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse create(@RequestBody @Valid NewUserRequest newUserRequest){
        User user = mapper.map(newUserRequest, User.class);
        user.setPassword(passwordEncoder.encode(newUserRequest.getPassword()));
        user = this.userRepository.save(user);

        return this.mapper.map(user, UserResponse.class);
    }

    @PutMapping("{userId}")
    UserResponse update(@PathVariable Long userId,
                        @RequestBody @Valid NewUserRequest newUserRequest){
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        this.mapper.map(newUserRequest, user);
        this.userRepository.save(user);

        return this.mapper.map(user, UserResponse.class);
    }

    @DeleteMapping("{userId}")
    void delete(@PathVariable Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        userRepository.delete(user);
    }
}
