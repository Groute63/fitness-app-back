package com.ssau.study.core.repository;

import com.ssau.study.core.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcStudentRepository implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Student> studentMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setBirthdate(rs.getDate("birthdate"));
        student.setNumber(rs.getInt("number"));
        return student;
    };

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from public.students", Integer.class);
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select * from public.students", studentMapper);
    }

    @Override
    public List<Student> findAllByName(String name) {
        return namedParameterJdbcTemplate.query("select * from public.students where name ilike '%' || :name || '%'",
                Collections.singletonMap("name", name), studentMapper);
    }

    @Override
    public Student findById(long id) {
        return jdbcTemplate.query("select * from public.students where id = ?", new Object[]{id},
                studentMapper).stream().findAny().orElse(null);
    }

    public Long addStudent(Student student) {
        return jdbcTemplate.queryForObject(
                "INSERT INTO students VALUES (nextval('students_id_seq'),?,?,?) returning id",
                new Object[]{student.getName(), student.getBirthdate(), student.getNumber()},
                Long.class);
    }

    @Override
    public Long deleteById(long id) {
        return jdbcTemplate.queryForObject(
                "DELETE FROM students  where id = ? returning id",
                new Object[]{id},
                Long.class);
    }

    @Override
    public Student updateStudent(Student student) {
        return jdbcTemplate.queryForObject(
                "update students set name = ?, birthdate = ? , number = ? where id = ? returning *",
                new Object[]{student.getName(), student.getBirthdate(), student.getNumber(), student.getId()},
                studentMapper);

    }
}
