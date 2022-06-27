package com.darshil.spring.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}























