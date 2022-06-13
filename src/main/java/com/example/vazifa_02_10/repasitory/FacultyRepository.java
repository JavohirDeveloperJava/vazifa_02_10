package com.example.vazifa_02_10.repasitory;


import com.example.vazifa_02_10.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    boolean existsByNameAndUniversityId(String name, Integer university_id);

    List<Faculty> findAllByUniversityId(Integer university_id);


}