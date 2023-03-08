package com.ssau.study.service;

import com.ssau.study.entity.Group;
import com.ssau.study.entity.Student;
import com.ssau.study.pojo.GroupPojo;
import com.ssau.study.pojo.StudentPojo;
import com.ssau.study.repository.GroupRepository;
import com.ssau.study.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    private final StudentRepository studentRepository;


    public List<GroupPojo> findAll(String name) {
        List<GroupPojo> result = new ArrayList<>();
        for (Group group : name == null ? groupRepository.findAll() : groupRepository.findAllByNameContainingIgnoreCase(name)) {
            result.add(GroupPojo.fromEntity(group));
        }
        return result;
    }

    public GroupPojo findById(Long id) {
        var group = groupRepository.findById(id);
        return group.map(GroupPojo::fromEntity).orElse(null);//group.isPresent() ? GroupPojo.fromEntity(group.get()) : null;
    }


    public GroupPojo create(GroupPojo groupPojo) {
        Group group = GroupPojo.toEntity(groupPojo);
        List<Student> students = groupPojo.getStudents().stream().map(StudentPojo::toEntity).collect(Collectors.toList());
        for(Student std: students){
            std.setGroup(group);
        }
        group.setStudents(students);
        return GroupPojo.fromEntity(groupRepository.save(group));
    }
}

