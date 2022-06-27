package com.darshil.spring.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// to indicate this entity must be mapped to the DB - whatever we do with this 'Student' entity will be reflected in DB
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
// to add builder pattern for this entity class
@Builder
// below we are defining what the table in the DB must be called and...
// that the emailId column must have unique values for each student
@Table(
        name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {
    // @Id annotation indicates this field is the primary key for this table
    @Id
    // how the sequences should be created
    @SequenceGenerator(
            name = "student_sequence", // name of the sequence generator
            sequenceName = "student_sequence", // name of the sequence
            allocationSize = 1
    )
    // how to generate those particular values for this studentId
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE, // because we are using sequence to generate the values
            generator = "student_sequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(
            name = "email_address",
            nullable = false // this means that we should be getting this particular value (cannot proceed without it)
    )
    private String emailId;

    @Embedded
    private Guardian guardian;

}
