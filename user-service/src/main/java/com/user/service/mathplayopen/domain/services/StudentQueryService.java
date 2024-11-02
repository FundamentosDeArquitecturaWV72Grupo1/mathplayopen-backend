package com.user.service.mathplayopen.domain.services;

import com.user.service.mathplayopen.application.external.feignclient.model.UserDto;
import com.user.service.mathplayopen.domain.model.aggregates.Student;
import com.user.service.mathplayopen.domain.model.queries.GetAllStudentsByInstructorIdQuery;
import com.user.service.mathplayopen.domain.model.queries.GetAllStudentsByInstitutionIdQuery;
import com.user.service.mathplayopen.domain.model.queries.GetAllStudentsQuery;
import com.user.service.mathplayopen.domain.model.queries.GetStudentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface StudentQueryService {
    Optional<Student> handle(GetStudentByIdQuery query);
    List<Student> handle(GetAllStudentsByInstructorIdQuery query);
    List<Student> handle(GetAllStudentsByInstitutionIdQuery query);
    List<Student> handle(GetAllStudentsQuery query);
    UserDto getUserByToken(String token);
}
