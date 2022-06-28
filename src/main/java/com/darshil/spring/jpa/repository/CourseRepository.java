package com.darshil.spring.jpa.repository;

import com.darshil.spring.jpa.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByCredit(
            int credit,
            PageRequest pageRequest
    );
}
