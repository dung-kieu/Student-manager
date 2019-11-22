package com.codegym.repository;

import com.codegym.model.Classroom;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository  extends PagingAndSortingRepository<Student, Long> {

    Iterable<Student> findAllByClassroom(Classroom classroom);

    Page<Student> findAllByFirstName(String firstName, Pageable pageable);

    Page<Student> findAllByOrderByFirstNameDesc(Pageable pageable);

    Page<Student> findAllByOrderByFirstNameAsc(Pageable pageable);
}
