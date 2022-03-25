package com.ecommercebd.user.application;


import com.ecommercebd.exception.NotFoundException;
import com.ecommercebd.mapper.Mapper;
import com.ecommercebd.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {

    private final Mapper mapper;
    private final UserRepository userRepository;

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




}
