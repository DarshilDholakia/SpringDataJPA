package com.darshil.spring.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course") // to remove call being made by toString() method to Course
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false // without course material you shouldn't be able to save a particular course - we are
            // specifying that this relationship is MANDATORY!!
    ) // specifying the mapping between CourseMaterial and Course
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId" // which attribute within entity is being referenced by above column name
    ) // for which particular column the foreign key wil be applied/which particular column can be used to join the 2 tables
    private Course course; // CourseMaterial containing Course since CourseMaterial cannot exist by itself
}
