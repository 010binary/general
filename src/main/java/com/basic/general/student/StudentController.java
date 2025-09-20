package com.basic.general.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @PostMapping
    public Student create(@RequestBody Student student){
        return studentService.create(student);
    }

    @DeleteMapping(path = "{studentId}")
    public String delete(@PathVariable long studentId){
        return studentService.delete(studentId);
    }

    @PutMapping(path = "{studentId}")
    public Student update(
            @PathVariable long studentId,
            @RequestBody Student updateData
    ){
        return studentService.update(studentId, updateData);
    }
}
