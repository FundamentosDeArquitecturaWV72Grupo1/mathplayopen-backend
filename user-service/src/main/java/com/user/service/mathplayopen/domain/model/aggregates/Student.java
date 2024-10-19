package com.user.service.mathplayopen.domain.model.aggregates;

import com.user.service.mathplayopen.domain.model.valueobjects.EmailAddress;
import com.user.service.mathplayopen.domain.model.valueobjects.Name;
import com.user.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.user.service.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@Entity(name = "student")
//@AllArgsConstructor
@NoArgsConstructor
public class Student extends AuditableAbstractAggregateRoot<Student> {
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Embedded
    @Column(name = "student_name")
    private Name name;

    @Embedded
    private EmailAddress email;

    @Column(name = "institution_id")
    private Long institutionId;

    @Column(name = "instructor_id")
    private Long instructorId;

    public Student(Long userId, Name name, EmailAddress email, Long institutionId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.institutionId = institutionId;
        this.instructorId = null;
    }


    public void assignInstructor(Long instructorId) {
        this.instructorId = instructorId;
    }
}
