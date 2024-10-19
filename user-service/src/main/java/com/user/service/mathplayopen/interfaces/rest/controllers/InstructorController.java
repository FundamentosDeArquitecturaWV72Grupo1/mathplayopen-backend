package com.user.service.mathplayopen.interfaces.rest.controllers;

import com.user.service.mathplayopen.application.external.resttemplate.TokenExtractor;
import com.user.service.mathplayopen.application.internal.dtos.InstructorDto;
import com.user.service.mathplayopen.application.external.feignclient.model.UserDto;
import com.user.service.mathplayopen.application.internal.mappers.InstructorMapper;
import com.user.service.mathplayopen.domain.model.aggregates.Instructor;
import com.user.service.mathplayopen.domain.model.commands.CreateInstructorCommand;
import com.user.service.mathplayopen.domain.model.queries.GetAllInstructorsByInstitutionIdQuery;
import com.user.service.mathplayopen.domain.model.queries.GetAllInstructorsQuery;
import com.user.service.mathplayopen.domain.model.queries.GetInstructorByIdQuery;
import com.user.service.mathplayopen.domain.model.valueobjects.EmailAddress;
import com.user.service.mathplayopen.domain.model.valueobjects.Name;
import com.user.service.mathplayopen.domain.services.InstructorCommandService;
import com.user.service.mathplayopen.domain.services.InstructorQueryService;
import com.user.service.mathplayopen.application.external.resttemplate.AuthenticationClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/api/v1/instructors", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Instructors", description = "Instructor Management Endpoints")
public class InstructorController {
    private final InstructorCommandService instructorCommandService;
    private final InstructorQueryService instructorQueryService;
    private final AuthenticationClient authenticationClient;
    private final TokenExtractor tokenExtractor;
    private static final Logger log = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    public InstructorController(InstructorCommandService instructorCommandService, InstructorQueryService instructorQueryService, AuthenticationClient authenticationClient, TokenExtractor tokenExtractor) {
        this.instructorCommandService = instructorCommandService;
        this.instructorQueryService = instructorQueryService;
        this.authenticationClient = authenticationClient;
        this.tokenExtractor = tokenExtractor;
    }

    @PostMapping("/register")
    public ResponseEntity<InstructorDto> createInstructor(@RequestBody InstructorDto instructorDto, HttpServletRequest request) {
        log.info("Received registration request for instructor: {}", instructorDto.email());
        String token = tokenExtractor.extractTokenFromRequest(request);
        log.info("Extracted token: {}", token);

        UserDto userDto = authenticationClient.getCurrentUser (token);
        if (userDto == null) {
            log.warn("Failed to authenticate user with token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        log.info("Authenticated user: {}", userDto.getUsername());
        CreateInstructorCommand command = new CreateInstructorCommand(
                userDto.getId(),
                new Name(instructorDto.firstName(), instructorDto.lastName()),
                new EmailAddress(instructorDto.email()),
                instructorDto.institutionId()
        );
        Instructor instructor = instructorCommandService.handle(command, token);
        return ResponseEntity.ok(InstructorMapper.toDto(instructor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDto> getInstructorById(@PathVariable Long id, HttpServletRequest request) {
        String token = tokenExtractor.extractTokenFromRequest(request);
        UserDto userDto = authenticationClient.getCurrentUser (token);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Instructor> instructor = instructorQueryService.handle(new GetInstructorByIdQuery(id));
        return instructor.map(i -> ResponseEntity.ok(InstructorMapper.toDto(i)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/institution/{institutionId}")
    public ResponseEntity<List<InstructorDto>> getInstructorsByInstitutionId(@PathVariable Long institutionId, HttpServletRequest request) {
        String token = tokenExtractor.extractTokenFromRequest(request);
        UserDto userDto = authenticationClient.getCurrentUser (token);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Instructor> instructors = instructorQueryService.handle(new GetAllInstructorsByInstitutionIdQuery(institutionId));
        List<InstructorDto> instructorDtos = instructors.stream().map(InstructorMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(instructorDtos);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InstructorDto>> getAllInstructors(HttpServletRequest request) {
        String token = tokenExtractor.extractTokenFromRequest(request);
        UserDto userDto = authenticationClient.getCurrentUser (token);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Instructor> instructors = instructorQueryService.handle(new GetAllInstructorsQuery());
        List<InstructorDto> instructorDtos = instructors.stream().map(InstructorMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(instructorDtos);
    }
}
