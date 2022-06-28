package com.darshil.spring.jpa.repository;

import com.darshil.spring.jpa.entity.Guardian;
import com.darshil.spring.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest - will help test repository layer and once this is finished, it will flush the new/updated data from DB
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .firstName("Wayne")
                .lastName("Rooney")
                .emailId("rooney@email.com")
//                .guardianEmail("guardian@email.com")
//                .guardianMobile("123456789")
//                .guardianName("Guardian Name")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Cristiano Ronaldo")
                .email("cr7@email.com")
                .mobile("123456789")
                .build();
        Student student = Student.builder()
                .firstName("Bruno")
                .lastName("Fernandes")
                .emailId("bfernandes@email.com")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("Jeff");
        System.out.println("studentList = " + students);
    }

    @Test
    // this will print out all the students with first names containing "An"
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("ma");
        System.out.println("studentList = " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName () {
        List<Student> students = studentRepository.findByGuardianName("Jeff Bezos");
        System.out.println("students = " + students);
    }

    @Test
    public void printGetStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddress("warrenbuffett@email.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress() {
        String studentName = studentRepository.getStudentFirstNameByEmailAddress("warrenbuffett@email.com");
        System.out.println("student = " + studentName);
    }

    @Test
    public void printGetStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailAddressNative("warrenbuffett@email.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printGetStudentByEmailAddressNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("warrenbuffett@email.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        int result = studentRepository.updateStudentNameByEmailId("Darshil", "warrenbuffett@email.com");
        System.out.println("result = " + result);
    }
}