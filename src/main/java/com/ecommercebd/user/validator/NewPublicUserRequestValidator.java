package com.ecommercebd.user.validator;

import com.ecommercebd.user.application.NewPublicUserRequest;
import com.ecommercebd.user.application.NewUserRequest;
import com.ecommercebd.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class NewPublicUserRequestValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewUserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NewPublicUserRequest newPublicUserRequest = (NewPublicUserRequest) target;

        if(userRepository.existsByEmail(newPublicUserRequest.getEmail())) {
            errors.rejectValue("email","","Já foi cadastrado um usuário com esse email!");
        }
    }
}
