package com.user.service.mathplayopen.domain.services;

import com.user.service.mathplayopen.domain.model.aggregates.Instructor;
import com.user.service.mathplayopen.domain.model.queries.GetAllInstructorsByInstitutionIdQuery;
import com.user.service.mathplayopen.domain.model.queries.GetAllInstructorsQuery;
import com.user.service.mathplayopen.domain.model.queries.GetInstructorByIdQuery;

import java.util.List;
import java.util.Optional;

public interface InstructorQueryService {
    Optional<Instructor> handle(GetInstructorByIdQuery query);
    List<Instructor> handle(GetAllInstructorsByInstitutionIdQuery query);
    List<Instructor> handle(GetAllInstructorsQuery query);
}
