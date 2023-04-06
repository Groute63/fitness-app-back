package com.ssau.study.core.repository;

import com.ssau.study.core.entity.Student;

import java.util.List;

public interface StudentRepository {
    int count();

    List<Student> findAll();

    List<Student> findAllByName(String name);

    Student findById(long id);

    Long addStudent(Student student);

    Long deleteById(long id);

    Student updateStudent(Student student);
}
