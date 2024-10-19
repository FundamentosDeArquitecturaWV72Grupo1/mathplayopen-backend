package com.user.service.mathplayopen.application.internal.mappers;

import com.user.service.mathplayopen.application.internal.dtos.InstructorDto;
import com.user.service.mathplayopen.domain.model.aggregates.Instructor;
import com.user.service.mathplayopen.domain.model.valueobjects.EmailAddress;
import com.user.service.mathplayopen.domain.model.valueobjects.Name;

public class InstructorMapper {
    public static Instructor toEntity(InstructorDto dto) {
        return new Instructor(
                null,
                new Name(dto.firstName(), dto.lastName()),
                new EmailAddress(dto.email()),
                dto.institutionId()
        );
    }

    public static InstructorDto toDto(Instructor instructor) {
        return new InstructorDto(
                instructor.getName().firstName(),
                instructor.getName().lastName(),
                instructor.getEmail().email(),
                instructor.getInstitutionId()
        );
    }
}
