package com.ssau.study.core.repository.jparepository;

import com.ssau.study.core.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepositoryJPA extends JpaRepository<Group, Long> {
    List<Group> findAllByNameContainingIgnoreCase(String name);
}
