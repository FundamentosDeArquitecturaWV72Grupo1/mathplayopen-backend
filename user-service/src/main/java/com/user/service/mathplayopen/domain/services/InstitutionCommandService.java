package com.user.service.mathplayopen.domain.services;

import com.user.service.mathplayopen.domain.model.aggregates.EducationalInstitution;
import com.user.service.mathplayopen.domain.model.commands.CreateInstitutionCommand;

public interface InstitutionCommandService {
    EducationalInstitution handle(CreateInstitutionCommand command);
}
