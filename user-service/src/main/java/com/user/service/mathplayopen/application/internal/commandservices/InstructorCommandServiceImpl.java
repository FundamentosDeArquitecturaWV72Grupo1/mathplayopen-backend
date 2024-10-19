package com.user.service.mathplayopen.application.internal.commandservices;

import com.user.service.mathplayopen.application.external.feignclient.model.UserDto;
import com.user.service.mathplayopen.domain.exceptions.InstitutionNotFoundException;
import com.user.service.mathplayopen.domain.model.aggregates.Instructor;
import com.user.service.mathplayopen.domain.model.commands.CreateInstructorCommand;
import com.user.service.mathplayopen.domain.model.events.InstructorRegisteredEvent;
import com.user.service.mathplayopen.domain.services.InstructorCommandService;
import com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories.InstitutionRepository;
import com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories.InstructorRepository;
import com.user.service.mathplayopen.application.external.resttemplate.AuthenticationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorCommandServiceImpl implements InstructorCommandService {
    private final InstructorRepository instructorRepository;
    private final InstitutionRepository institutionRepository;
    private final AuthenticationClient authenticationClient;

    @Autowired
    public InstructorCommandServiceImpl(InstructorRepository instructorRepository, InstitutionRepository institutionRepository, AuthenticationClient authenticationClient) {
        this.instructorRepository = instructorRepository;
        this.institutionRepository = institutionRepository;
        this.authenticationClient = authenticationClient;
    }

    @Override
    public Instructor handle(CreateInstructorCommand command, String token) {
        UserDto userDto = authenticationClient.getCurrentUser(token);
        if (userDto == null || !authenticationClient.hasRole(token, "ROLE_INSTRUCTOR")) {
            throw new RuntimeException("User is not authorized to register as a instructor");
        }

        institutionRepository.findById(command.institutionId())
                .orElseThrow(() -> new InstitutionNotFoundException(command.institutionId()));

        Instructor instructor = new Instructor(userDto.getId(),command.name(), command.email(), command.institutionId());
        Instructor savedInstructor = instructorRepository.save(instructor);

        savedInstructor.publishEvent(new InstructorRegisteredEvent(savedInstructor.getId(), savedInstructor.getName(), savedInstructor.getEmail(), savedInstructor.getInstitutionId()));
        return savedInstructor;
    }
}
