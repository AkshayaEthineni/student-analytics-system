package com.student.studentanalytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // JPA gives CRUD for free — nothing needed here yet
}
