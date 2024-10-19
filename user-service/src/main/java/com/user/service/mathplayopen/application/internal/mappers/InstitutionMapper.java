package com.user.service.mathplayopen.application.internal.mappers;

import com.user.service.mathplayopen.application.internal.dtos.InstitutionDto;
import com.user.service.mathplayopen.domain.model.aggregates.EducationalInstitution;
import com.user.service.mathplayopen.domain.model.valueobjects.Address;

public class InstitutionMapper {
    public static EducationalInstitution toEntity(InstitutionDto dto) {
        return new EducationalInstitution(
                dto.name(),
                new Address(dto.street(), dto.city(), dto.state())
        );
    }

    public static InstitutionDto toDto(EducationalInstitution institution) {
        return new InstitutionDto(
                institution.getName(),
                institution.getAddress().street(),
                institution.getAddress().city(),
                institution.getAddress().state()
        );
    }
}
