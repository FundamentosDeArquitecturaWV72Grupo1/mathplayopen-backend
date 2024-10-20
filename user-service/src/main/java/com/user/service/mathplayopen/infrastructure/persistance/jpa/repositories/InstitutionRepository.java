package com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories;

import com.user.service.mathplayopen.domain.model.aggregates.EducationalInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<EducationalInstitution, Long> {
    //Optional<EducationalInstitution> findByEmail(EmailAddress email);

    //Optional<EducationalInstitution> findByInstitutionId(Long instructorId);
}
