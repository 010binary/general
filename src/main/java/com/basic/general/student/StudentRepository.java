package com.basic.general.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT a FROM Student a WHERE a.email = ?1")
     Optional<Student> findStudentByEmail(String email);
}
