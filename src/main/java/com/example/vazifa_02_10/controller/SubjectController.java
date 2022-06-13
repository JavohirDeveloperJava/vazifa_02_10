package com.example.vazifa_02_10.controller;

import com.example.vazifa_02_10.entity.Subject;
import com.example.vazifa_02_10.repasitory.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    //CREATE
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exist";
        subjectRepository.save(subject);
        return "Subject added";
    }

    //READ
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }



}