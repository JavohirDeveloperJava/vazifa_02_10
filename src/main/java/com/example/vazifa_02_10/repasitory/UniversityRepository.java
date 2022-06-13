package com.example.vazifa_02_10.repasitory;

import com.example.vazifa_02_10.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
