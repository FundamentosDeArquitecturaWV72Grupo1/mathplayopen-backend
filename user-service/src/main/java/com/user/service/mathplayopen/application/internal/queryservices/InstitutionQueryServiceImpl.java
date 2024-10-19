package com.user.service.mathplayopen.application.internal.queryservices;

import com.user.service.mathplayopen.domain.model.aggregates.EducationalInstitution;
import com.user.service.mathplayopen.domain.model.queries.GetAllInstitutionsQuery;
import com.user.service.mathplayopen.domain.model.queries.GetInstitutionByIdQuery;
import com.user.service.mathplayopen.domain.services.InstitutionQueryService;
import com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionQueryServiceImpl implements InstitutionQueryService {
    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionQueryServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Optional<EducationalInstitution> handle(GetInstitutionByIdQuery query) {
        return institutionRepository.findById(query.institutionId());
    }

    @Override
    public List<EducationalInstitution> handle(GetAllInstitutionsQuery query) {
        return institutionRepository.findAll();
    }
}
