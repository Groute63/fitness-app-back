package com.ssau.study.service;

import com.ssau.study.entity.Student;
import com.ssau.study.pojo.StudentPojo;
import com.ssau.study.repository.jparepository.GroupRepositoryJPA;
import com.ssau.study.repository.jparepository.StudentRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final GroupRepositoryJPA groupRepository;

    private final StudentRepositoryJPA studentRepository;

    public List<StudentPojo> findAll(String name) {
        List<StudentPojo> result = new ArrayList<>();
        for (Student student : name == null ? studentRepository.findAll() : studentRepository.findAllByNameContainingIgnoreCase(name)) {
            result.add(StudentPojo.fromEntity(student));
        }
        return result;
    }

    public StudentPojo findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(StudentPojo::fromEntity).orElse(null);
    }

    public StudentPojo create(long groupId, StudentPojo pojo) {
        Student student = StudentPojo.toEntity(pojo);
        student.setGroup(groupRepository.findById(groupId).orElseThrow());
        return StudentPojo.fromEntity(studentRepository.save(student));
    }

    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }

    public StudentPojo update(StudentPojo studentPojo, long groupId) {
        Student student = StudentPojo.toEntity(studentPojo);
        if (groupId != 0)
            student.setGroup(groupRepository.findById(groupId).orElseThrow());
        return StudentPojo.fromEntity(studentRepository.save(student));
    }
}

