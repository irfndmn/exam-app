package com.dmn.security.service;

import com.dmn.entity.Student;
import com.dmn.entity.Teacher;
import com.dmn.entity.User;
import com.dmn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authority(user));
    }

    private List<SimpleGrantedAuthority> authority(User user) {

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        if (user instanceof Teacher) {
            authorityList = ((Teacher) user).getUserRole().stream().map(t -> new SimpleGrantedAuthority(t.getType().name())).collect(Collectors.toList());

        } else if (user instanceof Student) {

            authorityList.add(new SimpleGrantedAuthority(((Student) user).getRole().getType().name()));

        }
        return authorityList;
    }
}


