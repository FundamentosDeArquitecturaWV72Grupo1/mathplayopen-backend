package com.user.service.mathplayopen.domain.model.aggregates;

import com.user.service.mathplayopen.domain.model.valueobjects.Address;
import com.user.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "institution")
@AllArgsConstructor
@NoArgsConstructor
public class EducationalInstitution extends AuditableAbstractAggregateRoot<EducationalInstitution> {
    @Column(name = "institution_name")
    private String name;

    @Embedded
    private Address address;
}
