package com.dmn.repository;

import com.dmn.entity.Role;
import com.dmn.entity.enums.RoleType;
import com.dmn.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role,Integer> {


    Optional<Role> findByType(RoleType type) throws ResourceNotFoundException;
}
