package com.darshil.spring.jpa.repository;

import com.darshil.spring.jpa.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.awt.print.Pageable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

//    @Test
//    public void saveCourse() {
//        Teacher teacher = Teacher.builder()
//                .firstName("Nelson")
//                .lastName("Amigoscode")
//                .build();
//
//        CourseMaterial courseMaterial = CourseMaterial.builder()
//                .url("www.lombok.com")
//                .build();
//
//        Course course = Course.builder()
//                .title("Lombok")
//                .credit(2)
//                .courseMaterial(courseMaterial)
//                .teacher(teacher)
//                .build();
//        courseRepository.save(course);
//    }

    @Test
    public void printCourses() {
        List<Course> courseList = courseRepository.findAll();
        System.out.println("courseList = " + courseList);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Nelson")
                .lastName("Amigoscode")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        PageRequest firstPageWithOneRecord = PageRequest.of(0,1);
        List<Course> courses = courseRepository.findAll(firstPageWithOneRecord).getContent();

        Long totalElements = courseRepository.findAll(firstPageWithOneRecord).getTotalElements();
        int totalPages = courseRepository.findAll(firstPageWithOneRecord).getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting() {
        PageRequest sortByTitle = PageRequest.of(0, 1, Sort.by("title"));
        PageRequest sortByCredit = PageRequest.of(0, 1, Sort.by("credit").descending());
        PageRequest sortByTitleAndCreditDesc = PageRequest.of(0, 1, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> titleSortedCourses = courseRepository.findAll(sortByTitle).getContent();
        List<Course> creditSortedCourses = courseRepository.findAll(sortByCredit).getContent();
        List<Course> titleCreditSortedCourses = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();

        System.out.println("titleCreditSortedCourses = " + titleCreditSortedCourses);
        System.out.println("creditSortedCourses = " + creditSortedCourses);
        System.out.println("titleSortedCourses = " + titleSortedCourses);
    }

    @Test
    public void printFindByTitleContaining() {
        PageRequest firstPageFiveRecords = PageRequest.of(0,5);

        List<Course> courses = courseRepository.findByCredit(6, firstPageFiveRecords).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Colin")
                .lastName("Farquhar")
                .build();

        Guardian guardian = Guardian.builder()
                .name("Erik Ten Hag")
                .email("eth@email.com")
                .mobile("123456789")
                .build();

        Student student = Student.builder()
                .firstName("Jadon")
                .lastName("Sancho")
                .emailId("js7@email.com")
                .guardian(guardian)
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);
        courseRepository.save(course);
    }
}