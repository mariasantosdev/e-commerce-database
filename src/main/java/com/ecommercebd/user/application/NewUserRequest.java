package com.ecommercebd.user.application;

import com.ecommercebd.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String phone;
    @NotBlank
    @CPF
    private String cpf;
    @NotNull
    private Role role = Role.CLIENT;

}
