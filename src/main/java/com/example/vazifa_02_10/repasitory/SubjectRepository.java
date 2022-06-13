package com.example.vazifa_02_10.repasitory;

import com.example.vazifa_02_10.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    boolean existsByName(String name);

}
