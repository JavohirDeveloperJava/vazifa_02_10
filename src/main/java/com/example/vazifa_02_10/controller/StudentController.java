package com.example.vazifa_02_10.controller;

import com.example.vazifa_02_10.entity.Address;
import com.example.vazifa_02_10.entity.Group;
import com.example.vazifa_02_10.entity.Student;
import com.example.vazifa_02_10.entity.Subject;
import com.example.vazifa_02_10.payload.StudentDto;
import com.example.vazifa_02_10.repasitory.AddressRepository;
import com.example.vazifa_02_10.repasitory.GroupRepository;
import com.example.vazifa_02_10.repasitory.StudentRepository;
import com.example.vazifa_02_10.repasitory.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SubjectRepository subjectRepository;

    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //2. UNIVERSITY
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId,
                                                     @RequestParam int page) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from  student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }

    //3. FACULTY DEKANAT
    @GetMapping("/forFaculty/{facId}")
    public Page<Student> getStudentFacId(@PathVariable Integer facId,@RequestParam int page ){
        Pageable pageable=PageRequest.of(page,10);
        Page<Student> allByGroup_facultyId = studentRepository.findAllByGroup_FacultyId(facId, pageable);
        return allByGroup_facultyId;
    }

    //4. GROUP OWNER
    @GetMapping("/forGroup/{groupId}")
    public Page<Student> getStudentGroupId(@PathVariable Integer groupId,@RequestParam int page){
        Pageable pageable=PageRequest.of(page,10);
        Page<Student> allByGroupId = studentRepository.findAllByGroupId(groupId, pageable);
        return allByGroupId;
    }

    @DeleteMapping("/delet/{id}")
    public String delet(@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            studentRepository.deleteById(id);
            return "Student deleted";
        }
        return "bunday student yoq";
    }

    @PutMapping("/put/{id}")
    public String put(@PathVariable Integer id, @RequestBody StudentDto dto){
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()){
            Student student = optional.get();
            student.setFirstName(dto.getFirstName());
            student.setLastName(dto.getLastName());
            Optional<Address> optionalAddress = addressRepository.findById(dto.getAddressList());
            student.setAddress(optionalAddress.get());
            Optional<Group> optionalGroup = groupRepository.findById(dto.getGroupList());
            student.setGroup(optionalGroup.get());
            List<Subject> allById = subjectRepository.findAllById(dto.getSubjectList());
            student.setSubjects(allById);
            return "Student ozgardi";
        }
        return "topilmadi";
    }


}