package com.ssau.study.controller;

import com.ssau.study.entity.Student;
import com.ssau.study.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/count")
    public int count() {
        return studentRepository.count();
    }

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/findByName/{name}")
    public List<Student> findAllByName(@PathVariable String name) {
        return studentRepository.findAllByName(name);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable long id) {
        return studentRepository.findById(id);
    }

    @PutMapping
    public Long addStudent(@RequestBody Student student) {
        return studentRepository.addStudent(student);
    }

    @DeleteMapping("/{id}")
    public Long deleteStudent(@PathVariable long id) {
        return studentRepository.deleteById(id);
    }

    @PostMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentRepository.updateStudent(student);
    }

}
