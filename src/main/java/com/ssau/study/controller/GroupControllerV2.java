package com.ssau.study.controller;

import com.ssau.study.entity.Student;
import com.ssau.study.pojo.GroupPojo;
import com.ssau.study.pojo.StudentPojo;
import com.ssau.study.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group/v2")
public class GroupControllerV2 {

    private final GroupService groupService;

    public GroupControllerV2(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/getAll")
    public List<GroupPojo> findAllGroup() {
        return groupService.findAll(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupPojo> findGroupById(@PathVariable long id) {
        GroupPojo group = groupService.findById(id);
        if (group == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping()
    public GroupPojo createGroup(@RequestBody GroupPojo group) {
        return groupService.create(group);
    }

    @PostMapping("/addStudent/{id}")
    public StudentPojo addGroup(@PathVariable long id,@RequestBody StudentPojo student) {
        return groupService.addStudent(id,student);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable long id) {
        groupService.delete(id);
    }

    @PutMapping()
    public GroupPojo updateGroup(@RequestBody GroupPojo group) {
        return groupService.update(group);
    }
}
