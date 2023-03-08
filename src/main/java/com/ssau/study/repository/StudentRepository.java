package com.ssau.study.repository;

import com.ssau.study.entity.Group;
import com.ssau.study.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long>{
    List<Student> findAllByNameContainingIgnoreCase(String name);

    @Modifying
    @Query("delete from Student where id = ?1")
    int deleteById(long id);
    //int count();


    //List<Student> findAllByName(String name);


    /*Student addStudent(Student std);

    boolean deleteStudent(long id);

    Student updateStudent(Student std);

     */
}
