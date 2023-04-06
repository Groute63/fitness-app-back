package com.ssau.study.core.repository.jparepository;

import com.ssau.study.core.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepositoryJPA extends JpaRepository<Student, Long> {
    List<Student> findAllByNameContainingIgnoreCase(String name);
}
