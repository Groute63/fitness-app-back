package com.ssau.study.controller;

import com.ssau.study.entity.Student;
import com.ssau.study.pojo.StudentPojo;
import com.ssau.study.repository.StudentRepository;
import com.ssau.study.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping()
    public List<StudentPojo> findAllByNameContainingIgnoreCase(@RequestBody(required = false) String name){
        return studentService.findAll(name);
    }
    @GetMapping("/{id}")
    public StudentPojo findById(@PathVariable long id) {
        return studentService.findById(id);
    }

    @PostMapping()
    public StudentPojo add(@RequestParam long groupId, @RequestBody StudentPojo studentPojo){
        return studentService.create(groupId,studentPojo);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id){
        return studentService.deleteById(id);
    }
    @PutMapping()
    public StudentPojo update(@RequestParam long groupId,@RequestBody StudentPojo studentPojo)
    {
        return studentService.update(studentPojo,groupId);
    }
    /*@GetMapping("/count")
    public int count() {
        return studentRepository.count();
    }

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable int id) {return studentRepository.findById(id);}

    @PostMapping
    public Student addStudent(@RequestBody Student std) {return studentRepository.addStudent(std);}

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable int id) {return studentRepository.deleteStudent(id);}

    @PutMapping
    public Student updateStudent(@RequestBody Student std) {return studentRepository.updateStudent(std);}

     */
}

