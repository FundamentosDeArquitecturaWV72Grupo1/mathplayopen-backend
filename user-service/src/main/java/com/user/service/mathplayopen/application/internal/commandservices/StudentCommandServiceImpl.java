package com.user.service.mathplayopen.application.internal.commandservices;

import com.user.service.mathplayopen.application.external.feignclient.model.UserDto;
import com.user.service.mathplayopen.domain.exceptions.InstitutionNotFoundException;
import com.user.service.mathplayopen.domain.exceptions.InstructorNotFoundException;
import com.user.service.mathplayopen.domain.exceptions.StudentNotFoundException;
import com.user.service.mathplayopen.domain.model.aggregates.Instructor;
import com.user.service.mathplayopen.domain.model.aggregates.Student;
import com.user.service.mathplayopen.domain.model.commands.AssignInstructorToStudentCommand;
import com.user.service.mathplayopen.domain.model.commands.CreateStudentCommand;
import com.user.service.mathplayopen.domain.model.events.InstructorAssignedToStudentEvent;
import com.user.service.mathplayopen.domain.model.events.StudentRegisteredEvent;
import com.user.service.mathplayopen.domain.services.StudentCommandService;
import com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories.InstitutionRepository;
import com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories.InstructorRepository;
import com.user.service.mathplayopen.infrastructure.persistance.jpa.repositories.StudentRepository;
import com.user.service.mathplayopen.application.external.resttemplate.AuthenticationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {
    private final StudentRepository studentRepository;
    private final InstitutionRepository institutionRepository;
    private final InstructorRepository instructorRepository;
    private final AuthenticationClient authenticationClient;

    @Autowired
    public StudentCommandServiceImpl(StudentRepository studentRepository, InstitutionRepository institutionRepository, InstructorRepository instructorRepository, AuthenticationClient authenticationClient) {
        this.studentRepository = studentRepository;
        this.institutionRepository = institutionRepository;
        this.instructorRepository = instructorRepository;
        this.authenticationClient = authenticationClient;
    }

    @Override
    public Student handle(CreateStudentCommand command, String token) {
        UserDto userDto = authenticationClient.getCurrentUser(token);
        if (userDto == null || !authenticationClient.hasRole(token, "ROLE_STUDENT")) {
            throw new RuntimeException("User is not authorized to register as a student");
        }

        institutionRepository.findById(command.institutionId())
                .orElseThrow(() -> new InstitutionNotFoundException(command.institutionId()));

        Student student = new Student(userDto.getId(), command.name(), command.email(), command.institutionId());
        Student savedStudent = studentRepository.save(student);

        savedStudent.publishEvent(new StudentRegisteredEvent(savedStudent.getId(), savedStudent.getName(), savedStudent.getEmail(), savedStudent.getInstitutionId()));
        return savedStudent;
    }

    @Override
    public void handle(AssignInstructorToStudentCommand command) {
        Student student = studentRepository.findById(command.studentId())
                .orElseThrow(() -> new StudentNotFoundException(command.studentId()));
        Instructor instructor = instructorRepository.findById(command.instructorId())
                .orElseThrow(() -> new InstructorNotFoundException(command.instructorId()));

        student.assignInstructor(command.instructorId());
        studentRepository.save(student);

        student.publishEvent(new InstructorAssignedToStudentEvent(student.getId(), instructor.getId()));
    }
}
