package com.basic.general.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(){
       return studentRepository.findAll();
    }

    public Student create(Student student) {
       Optional<Student> studentExist =  studentRepository.findStudentByEmail(student.getEmail());

       if (studentExist.isPresent()) throw new IllegalStateException("Email is already in use");

       return studentRepository.save(student);
    }

    public String delete(long studentId) {
        boolean studentExist = studentRepository.existsById(studentId);

        if (!studentExist) throw new IllegalStateException("Student Doesn't exist");

        studentRepository.deleteById(studentId);

        return "Deleted successfully";
    }

    @Transactional
    public Student update(long studentId, Student updateData) {
        boolean studentExist = studentRepository.existsById(studentId);

        if (!studentExist) throw new IllegalStateException("Student Doesn't exist");


    }
}
