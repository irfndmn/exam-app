package com.dmn.dto.response;

import com.dmn.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    public StudentResponse(Student s) {
        this.id = s.getId();
        this.name = s.getName();
        this.surname = s.getSurname();
        this.phoneNumber = s.getPhoneNumber();
        this.email = s.getEmail();
    }
}
