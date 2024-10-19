package com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories;

import com.user.service.mathplayopen.domain.model.aggregates.Instructor;
import com.user.service.mathplayopen.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    //Optional<Instructor> findByEmail(EmailAddress email);

    //Optional<Instructor> findByInstructorId(Long instructorId);

    List<Instructor> findAllByInstitutionId(Long institutionId);
}
