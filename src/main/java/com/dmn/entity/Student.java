package com.dmn.entity;

import com.dmn.dto.requests.StudentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student extends User{

    @Email(message = "provide valid email")
    @Column(unique = true,nullable = false)
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;


}
