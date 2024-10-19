package com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories;

import com.user.service.mathplayopen.domain.model.aggregates.Student;
import com.user.service.mathplayopen.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //Optional<Student> findByEmail(EmailAddress email);
    //Optional<Student> findByStudentId(Long studentId);
    List<Student> findAllByInstructorId(Long instructorId);
    List<Student> findAllByInstitutionId(Long institutionId);
}
