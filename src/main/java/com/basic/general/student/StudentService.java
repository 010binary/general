package com.basic.general.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.basic.general.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<ApiResponse<List<Student>>> findAll() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(ApiResponse.success("Students retrieved successfully", students));
    }

    public ResponseEntity<ApiResponse<Student>> create(Student student) {
        Optional<Student> studentExist = studentRepository.findStudentByEmail(student.getEmail());

        if (studentExist.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error("Email is already in use"));

        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Student created successfully", savedStudent));
    }

    public ResponseEntity<ApiResponse<String>> delete(long studentId) {
        boolean studentExist = studentRepository.existsById(studentId);

        if (!studentExist)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Student doesn't exist"));

        studentRepository.deleteById(studentId);

        return ResponseEntity.ok(ApiResponse.success("Student deleted successfully", "Deleted successfully"));
    }

    @Transactional
    public ResponseEntity<ApiResponse<Student>> update(long studentId, Student updateData) {
        Optional<Student> existingStudentOpt = studentRepository.findById(studentId);

        if (existingStudentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Student with id " + studentId + " doesn't exist"));
        }

        Student existingStudent = existingStudentOpt.get();

        // Update name if provided
        if (updateData.getName() != null && !updateData.getName().trim().isEmpty()) {
            existingStudent.setName(updateData.getName());
        }

        // Update email if provided and different from current
        if (updateData.getEmail() != null && !updateData.getEmail().trim().isEmpty()) {
            if (!updateData.getEmail().equals(existingStudent.getEmail())) {
                // Check if the new email is already in use by another student
                Optional<Student> studentWithEmail = studentRepository.findStudentByEmail(updateData.getEmail());
                if (studentWithEmail.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(ApiResponse.error("Email is already in use by another student"));
                }
                existingStudent.setEmail(updateData.getEmail());
            }
        }

        // Update date of birth if provided
        if (updateData.getDob() != null) {
            existingStudent.setDob(updateData.getDob());
        }

        // No need to explicitly save since we're using @Transactional
        // The changes will be automatically persisted when the transaction commits
        return ResponseEntity.ok(ApiResponse.success("Student updated successfully", existingStudent));
    }
}
