package com.darshil.spring.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;

    // here, we want a reference of CourseMaterial in Course to define 1-to-1 bidirectional mapping but since the 1-to-1
    // mapping with join column as foreign key we are instead saying that this particular 1-to-1 mapping is already defined
    // by the "course" attribute within CourseMaterial class
    @OneToOne(
            mappedBy = "course"
    ) // saying this is mapped by [name we gave to Course object] within Course Material class
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id", // what the attribute is called in the table (name of the column)
            referencedColumnName = "teacherId" // what the name of the field is in this app
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    // by using the below, we are creating a new table which will hold the relationship between the 2 entities
    // this 3rd table will have 2 columns
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

    public void addStudents(Student student) {
        if (students == null) students = new ArrayList<>();
        students.add(student);
    }
}























