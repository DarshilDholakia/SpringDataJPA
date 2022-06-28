package com.darshil.spring.jpa.repository;

import com.darshil.spring.jpa.entity.Course;
import com.darshil.spring.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        Course courseJava = Course.builder()
                .title("Java")
                .credit(8)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Teacher")
                .lastName("name")
//                .courses(Arrays.asList(courseDBA, courseJava))
                .build();

        teacherRepository.save(teacher);
    }
}