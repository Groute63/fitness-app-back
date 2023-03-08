package com.ssau.study.pojo;

import com.ssau.study.entity.Group;
import com.ssau.study.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GroupPojo {
    private long id;
    private String name;
    private List<StudentPojo> students;

    public static GroupPojo fromEntity(Group group) {
        GroupPojo pojo = new GroupPojo();
        pojo.setId(group.getId());
        pojo.setName(group.getName());

        List<StudentPojo> students = new ArrayList<>();
        pojo.setStudents(students);
        for (Student student : group.getStudents()) {
            students.add(StudentPojo.fromEntity(student));
        }

        return pojo;
    }
    public static Group toEntity(GroupPojo groupPojo){
        Group group = new Group();
        group.setId(groupPojo.getId());
        group.setName(groupPojo.getName());

        List<Student> students = new ArrayList<>();
        group.setStudents(students);
        for (StudentPojo student : groupPojo.getStudents()) {
            students.add(StudentPojo.toEntity(student));
        }
        return group;
    }
}
