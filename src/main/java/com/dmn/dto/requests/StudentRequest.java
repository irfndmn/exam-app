package com.dmn.dto.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    @NotBlank(message = "please provide name")
    @Size(min = 2, max = 20, message = "name must be between {min} and {max}")
    private String name;
    @NotBlank(message = "please provide surname")
    @Size(min = 2, max = 20, message = "surname must be between {min} and {max}")
    private String surname;

    @NotBlank(message = "please provide username")
    @Size(min = 4, max = 10, message = "username must be between {min} and {max}")
    private String username;

    @NotBlank(message = "please provide password")
    @Size(min = 4, max = 8, message = "password must be between {min} and {max}")
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotEmpty
    @Email(message = "please provide valid email")
    private String email;

}
