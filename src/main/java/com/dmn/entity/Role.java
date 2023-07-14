package com.dmn.entity;

import com.dmn.entity.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType type;
}
