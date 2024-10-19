package com.user.service.mathplayopen.domain.services;

import com.user.service.mathplayopen.domain.model.aggregates.Student;
import com.user.service.mathplayopen.domain.model.commands.AssignInstructorToStudentCommand;
import com.user.service.mathplayopen.domain.model.commands.CreateStudentCommand;

public interface StudentCommandService {
    Student handle(CreateStudentCommand command, String token);
    void handle(AssignInstructorToStudentCommand command);
}
