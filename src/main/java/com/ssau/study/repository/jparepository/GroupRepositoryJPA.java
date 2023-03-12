package com.ssau.study.repository.jparepository;

import com.ssau.study.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepositoryJPA extends JpaRepository<Group, Long> {
    List<Group> findAllByNameContainingIgnoreCase(String name);
}
