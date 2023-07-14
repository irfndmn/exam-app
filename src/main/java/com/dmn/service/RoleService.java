package com.dmn.service;

import com.dmn.entity.Role;
import com.dmn.entity.enums.RoleType;
import com.dmn.exception.ResourceNotFoundException;
import com.dmn.repository.RoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleDao roleDao;

    public Role findRoleByType(RoleType roleType) {
        return roleDao.
                findByType(roleType).
                orElseThrow(() -> new ResourceNotFoundException("role doesn't exist by type"));
    }

}
