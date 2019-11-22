package com.codegym.service;

import com.codegym.model.Classroom;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    Page<Student> findAll(Pageable pageable);

    Student findById(Long id);

    void save(Student student);

    void remove(Long id);

    Iterable<Student> findAllByClassroom(Classroom classroom);

    Page<Student> findAllByFirstName(String firstName, Pageable pageable);

    Page<Student> findAllByOrderByFirstNameDesc(Pageable pageable);

    Page<Student> findAllByOrderByFirstNameAsc(Pageable pageable);


}
