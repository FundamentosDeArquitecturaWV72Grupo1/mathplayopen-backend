package com.user.service.mathplayopen.application.internal.queryservices;

import com.user.service.mathplayopen.domain.model.aggregates.Instructor;
import com.user.service.mathplayopen.domain.model.queries.GetAllInstructorsByInstitutionIdQuery;
import com.user.service.mathplayopen.domain.model.queries.GetAllInstructorsQuery;
import com.user.service.mathplayopen.domain.model.queries.GetInstructorByIdQuery;
import com.user.service.mathplayopen.domain.services.InstructorQueryService;
import com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorQueryServiceImpl implements InstructorQueryService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorQueryServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Optional<Instructor> handle(GetInstructorByIdQuery query) {
        return instructorRepository.findById(query.instructorId());
    }

    @Override
    public List<Instructor> handle(GetAllInstructorsByInstitutionIdQuery query) {
        return instructorRepository.findAllByInstitutionId(query.institutionId());
    }

    @Override
    public List<Instructor> handle(GetAllInstructorsQuery query) {
        return instructorRepository.findAll();
    }
}
