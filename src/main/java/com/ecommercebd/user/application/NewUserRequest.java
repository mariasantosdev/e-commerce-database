package com.ecommercebd.user.application;

import com.ecommercebd.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String phone;
    @NotBlank
    private String cpf;

    private Role role;

}
