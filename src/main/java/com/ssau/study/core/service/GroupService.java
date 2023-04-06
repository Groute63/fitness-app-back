package com.ssau.study.core.service;

import com.ssau.study.core.entity.Group;
import com.ssau.study.core.entity.Student;
import com.ssau.study.core.pojo.GroupPojo;
import com.ssau.study.core.pojo.StudentPojo;
import com.ssau.study.core.repository.jparepository.GroupRepositoryJPA;
import com.ssau.study.core.repository.jparepository.StudentRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepositoryJPA groupRepository;
    private final StudentRepositoryJPA studentRepository;

    public List<GroupPojo> findAll(String name) {
        List<GroupPojo> result = new ArrayList<>();
        for (Group group : name == null ? groupRepository.findAll() : groupRepository.findAllByNameContainingIgnoreCase(name)) {
            result.add(GroupPojo.fromEntity(group));
        }
        return result;
    }

    public GroupPojo findById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.map(GroupPojo::fromEntity).orElse(null);
    }


    public GroupPojo create(GroupPojo groupPojo) {
        Group group = GroupPojo.toEntity(groupPojo);
        if (groupPojo.getStudents() != null) {
            List<Student> students = groupPojo.getStudents().stream().map(StudentPojo::toEntity).collect(Collectors.toList());
            for (Student std : students) {
                std.setGroup(group);
            }
            group.setStudents(students);
        }
        return GroupPojo.fromEntity(groupRepository.save(group));
    }

    public void delete(long id) {
        groupRepository.deleteById(id);
    }

    public StudentPojo addStudent(Long groupId, StudentPojo pojo){
            var student = StudentPojo.toEntity(pojo);
            student.setGroup(groupRepository.findById(groupId).orElseThrow());
            return StudentPojo.fromEntity(studentRepository.save(student));
    }

    public GroupPojo update(GroupPojo groupPojo) {
        Group group = GroupPojo.toEntity(groupPojo);
        return GroupPojo.fromEntity(groupRepository.save(group));
    }
}

