package com.dmn.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_name",nullable = false, length = 25)
    private String name;

    @Column(nullable = false, length = 25)
    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(nullable = false)
    private String phoneNumber;





}
