package com.user.service.mathplayopen.interfaces.rest.controllers;

import com.user.service.mathplayopen.application.external.resttemplate.TokenExtractor;
import com.user.service.mathplayopen.application.internal.dtos.StudentDto;
import com.user.service.mathplayopen.application.external.feignclient.model.UserDto;
import com.user.service.mathplayopen.application.internal.mappers.StudentMapper;
import com.user.service.mathplayopen.domain.model.aggregates.Student;
import com.user.service.mathplayopen.domain.model.commands.AssignInstructorToStudentCommand;
import com.user.service.mathplayopen.domain.model.commands.CreateStudentCommand;
import com.user.service.mathplayopen.domain.model.queries.GetAllStudentsByInstitutionIdQuery;
import com.user.service.mathplayopen.domain.model.queries.GetAllStudentsByInstructorIdQuery;
import com.user.service.mathplayopen.domain.model.queries.GetAllStudentsQuery;
import com.user.service.mathplayopen.domain.model.queries.GetStudentByIdQuery;
import com.user.service.mathplayopen.domain.model.valueobjects.EmailAddress;
import com.user.service.mathplayopen.domain.model.valueobjects.Name;
import com.user.service.mathplayopen.domain.services.StudentCommandService;
import com.user.service.mathplayopen.domain.services.StudentQueryService;
import com.user.service.mathplayopen.application.external.resttemplate.AuthenticationClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Students", description = "Student Management Endpoints")
public class StudentController {
    private final StudentCommandService studentCommandService;
    private final StudentQueryService studentQueryService;
    private final AuthenticationClient authenticationClient;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationClient.class);
    private final TokenExtractor tokenExtractor;

    @Autowired
    public StudentController(StudentCommandService studentCommandService, StudentQueryService studentQueryService, AuthenticationClient authenticationClient, TokenExtractor tokenExtractor) {
        this.studentCommandService = studentCommandService;
        this.studentQueryService = studentQueryService;
        this.authenticationClient = authenticationClient;
        this.tokenExtractor = tokenExtractor;
    }

    @PostMapping("/register")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto, HttpServletRequest request) {
        log.info("Received registration request for student | USER SERVICE: {}", studentDto.email());
        String token = tokenExtractor.extractTokenFromRequest(request);

        UserDto userDto = authenticationClient.getCurrentUser(token);
        System.out.println("User: " + userDto);

        if (userDto == null) {
            log.warn("Failed to authenticate user with token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        log.info("Authenticated user: {}", userDto.getUsername());
        CreateStudentCommand command = new CreateStudentCommand(
                userDto.getId(),
                new Name(studentDto.firstName(), studentDto.lastName()),
                new EmailAddress(studentDto.email()),
                studentDto.institutionId()
        );
        Student student = studentCommandService.handle(command, token);
        return ResponseEntity.ok(StudentMapper.toDto(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id, HttpServletRequest request) {
        String token = tokenExtractor.extractTokenFromRequest(request);
        UserDto userDto = authenticationClient.getCurrentUser(token);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Student> student = studentQueryService.handle(new GetStudentByIdQuery(id));
        return student.map(s -> ResponseEntity.ok(StudentMapper.toDto(s)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<StudentDto>> getStudentsByInstructorId(@PathVariable Long instructorId, HttpServletRequest request) {
        String token = tokenExtractor.extractTokenFromRequest(request);
        UserDto userDto = authenticationClient.getCurrentUser(token);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Student> students = studentQueryService.handle(new GetAllStudentsByInstructorIdQuery(instructorId));
        List<StudentDto> studentDtos = students.stream().map(StudentMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/institution/{institutionId}")
    public ResponseEntity<List<StudentDto>> getStudentsByInstitutionId(@PathVariable Long institutionId, HttpServletRequest request) {
        String token = tokenExtractor.extractTokenFromRequest(request);
        UserDto userDto = authenticationClient.getCurrentUser(token);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Student> students = studentQueryService.handle(new GetAllStudentsByInstitutionIdQuery(institutionId));
        List<StudentDto> studentDtos = students.stream().map(StudentMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(studentDtos);
    }

    @PostMapping("/{studentId}/assign-instructor/{instructorId}")
    public ResponseEntity<Void> assignInstructorToStudent(@PathVariable Long studentId, @PathVariable Long instructorId, HttpServletRequest request) {
        String token = tokenExtractor.extractTokenFromRequest(request);
        UserDto userDto = authenticationClient.getCurrentUser(token);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        studentCommandService.handle(new AssignInstructorToStudentCommand(studentId, instructorId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<Student> students = studentQueryService.handle(new GetAllStudentsQuery());
        List<StudentDto> studentDtos = students.stream().map(StudentMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/token")
    public String getToken(HttpServletRequest request) {
        String token = tokenExtractor.extractTokenFromRequest(request);
        if (token == null) {
            return "No token found in request";
        }
        return token;
    }

    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser (HttpServletRequest request) {
        String token = tokenExtractor.extractTokenFromRequest(request);
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDto userDto = studentQueryService.getUserByToken(token);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userDto);
    }
}
