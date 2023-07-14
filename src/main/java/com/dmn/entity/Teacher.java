package com.dmn.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher extends User{
    @Email(message = "please provide valid email")
    @Column(unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userT_roleT",joinColumns = @JoinColumn(name = "T_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> userRole=new HashSet<>();
}
