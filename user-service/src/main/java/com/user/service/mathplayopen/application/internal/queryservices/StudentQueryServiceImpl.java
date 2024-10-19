package com.user.service.mathplayopen.application.internal.queryservices;

import com.user.service.mathplayopen.domain.model.aggregates.Student;
import com.user.service.mathplayopen.domain.model.queries.GetAllStudentsByInstitutionIdQuery;
import com.user.service.mathplayopen.domain.model.queries.GetAllStudentsByInstructorIdQuery;
import com.user.service.mathplayopen.domain.model.queries.GetAllStudentsQuery;
import com.user.service.mathplayopen.domain.model.queries.GetStudentByIdQuery;
import com.user.service.mathplayopen.domain.services.StudentQueryService;
import com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentQueryServiceImpl implements StudentQueryService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentQueryServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Optional<Student> handle(GetStudentByIdQuery query) {
        return studentRepository.findById(query.studentId());
    }

    @Override
    public List<Student> handle(GetAllStudentsByInstructorIdQuery query) {
        return studentRepository.findAllByInstructorId(query.instructorId());
    }

    @Override
    public List<Student> handle(GetAllStudentsByInstitutionIdQuery query) {
        return studentRepository.findAllByInstitutionId(query.institutionId());
    }

    @Override
    public List<Student> handle(GetAllStudentsQuery query) {
        return studentRepository.findAll();
    }
}
