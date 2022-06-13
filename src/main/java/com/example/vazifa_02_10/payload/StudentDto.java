package com.example.vazifa_02_10.payload;

import com.example.vazifa_02_10.entity.Address;
import com.example.vazifa_02_10.entity.Group;
import com.example.vazifa_02_10.entity.Subject;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer addressList;
    private Integer groupList;
    private List<Integer> subjectList;
}
