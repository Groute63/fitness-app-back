package com.ssau.study.core.controller;

import com.ssau.study.core.service.StudentService;
import com.ssau.study.core.pojo.StudentPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/v2")
public class StudentControllerV2 {
    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<StudentPojo> findAllByNameContainingIgnoreCase(@RequestBody(required = false) String name) {
        return studentService.findAll(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentPojo> findById(@PathVariable long id) {
        StudentPojo studentPojo = studentService.findById(id);
        if (studentPojo == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(studentPojo, HttpStatus.OK);
    }

    @PostMapping()
    public StudentPojo add(@RequestParam long groupId, @RequestBody StudentPojo studentPojo) {
        return studentService.create(groupId, studentPojo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        studentService.deleteById(id);
    }

    @PutMapping()
    public StudentPojo update(@RequestParam long groupId, @RequestBody StudentPojo studentPojo) {
        return studentService.update(studentPojo, groupId);
    }
}

