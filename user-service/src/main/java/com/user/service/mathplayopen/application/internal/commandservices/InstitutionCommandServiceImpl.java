package com.user.service.mathplayopen.application.internal.commandservices;

import com.user.service.mathplayopen.domain.model.aggregates.EducationalInstitution;
import com.user.service.mathplayopen.domain.model.commands.CreateInstitutionCommand;
import com.user.service.mathplayopen.domain.model.events.InstitutionRegisteredEvent;
import com.user.service.mathplayopen.domain.services.InstitutionCommandService;
import com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionCommandServiceImpl implements InstitutionCommandService {
    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionCommandServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public EducationalInstitution handle(CreateInstitutionCommand command) {
        EducationalInstitution institution = new EducationalInstitution(command.name(), command.address());
        EducationalInstitution savedInstitution = institutionRepository.save(institution);

        savedInstitution.publishEvent(new InstitutionRegisteredEvent(savedInstitution.getId(), savedInstitution.getName(), savedInstitution.getAddress()));
        return savedInstitution;
    }
}
