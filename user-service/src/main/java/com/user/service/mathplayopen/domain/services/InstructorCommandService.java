package com.user.service.mathplayopen.domain.services;

import com.user.service.mathplayopen.domain.model.aggregates.Instructor;
import com.user.service.mathplayopen.domain.model.commands.CreateInstructorCommand;

public interface InstructorCommandService {
    Instructor handle(CreateInstructorCommand command, String token);
}
