package com.user.service.mathplayopen.domain.services;

import com.user.service.mathplayopen.domain.model.aggregates.EducationalInstitution;
import com.user.service.mathplayopen.domain.model.queries.GetAllInstitutionsQuery;
import com.user.service.mathplayopen.domain.model.queries.GetInstitutionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface InstitutionQueryService {
    Optional<EducationalInstitution> handle(GetInstitutionByIdQuery query);
    List<EducationalInstitution> handle(GetAllInstitutionsQuery query);
}
