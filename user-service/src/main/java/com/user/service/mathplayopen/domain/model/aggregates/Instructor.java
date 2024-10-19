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
@Entity(name = "instructor")
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends AuditableAbstractAggregateRoot<Instructor> {
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Embedded
    @Column(name = "instructor_name")
    private Name name;

    @Embedded
    private EmailAddress email;

    @Column(name = "institution_id")
    private Long institutionId;
}
