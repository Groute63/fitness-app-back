package com.ssau.study.repository.jparepository;

import com.ssau.study.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepositoryJPA extends JpaRepository<Student, Long> {
    List<Student> findAllByNameContainingIgnoreCase(String name);
}
