package com.user.service.mathplayopen.application.internal.mappers;

import com.user.service.mathplayopen.application.internal.dtos.StudentDto;
import com.user.service.mathplayopen.domain.model.aggregates.Student;
import com.user.service.mathplayopen.domain.model.valueobjects.EmailAddress;
import com.user.service.mathplayopen.domain.model.valueobjects.Name;

public class StudentMapper {
    public static Student toEntity(StudentDto dto) {
        return new Student(
                null,
                new Name(dto.firstName(), dto.lastName()),
                new EmailAddress(dto.email()),
                dto.institutionId()
        );
    }

    public static StudentDto toDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName().firstName(),
                student.getName().lastName(),
                student.getEmail().email(),
                student.getInstitutionId()
        );
    }
}
