package com.ssau.study.controller;

import com.ssau.study.entity.Student;
import com.ssau.study.pojo.GroupPojo;
import com.ssau.study.pojo.StudentPojo;
import com.ssau.study.repository.StudentRepository;
import com.ssau.study.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class GroupController {

    private final GroupService groupService;
    public GroupController(GroupService groupService){this.groupService=groupService;}
    @GetMapping("/groups")
    public List<GroupPojo> findAll() {
        return groupService.findAll(null);
    }
    @GetMapping("/groups/{id}")
    public GroupPojo findById(long id){
        return groupService.findById(id);
    }

    @PostMapping()
    public GroupPojo add(@RequestBody GroupPojo group){
        return groupService.create(group);
    }
}
