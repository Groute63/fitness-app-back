package com.ssau.study.repository;
import com.ssau.study.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long>{
    List<Group> findAllByNameContainingIgnoreCase(String name);

}
