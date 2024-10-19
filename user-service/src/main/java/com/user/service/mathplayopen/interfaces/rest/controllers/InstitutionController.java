package com.user.service.mathplayopen.interfaces.rest.controllers;

import com.user.service.mathplayopen.application.internal.dtos.InstitutionDto;
import com.user.service.mathplayopen.application.internal.mappers.InstitutionMapper;
import com.user.service.mathplayopen.domain.model.aggregates.EducationalInstitution;
import com.user.service.mathplayopen.domain.model.commands.CreateInstitutionCommand;
import com.user.service.mathplayopen.domain.model.queries.GetAllInstitutionsQuery;
import com.user.service.mathplayopen.domain.model.queries.GetInstitutionByIdQuery;
import com.user.service.mathplayopen.domain.model.valueobjects.Address;
import com.user.service.mathplayopen.domain.services.InstitutionCommandService;
import com.user.service.mathplayopen.domain.services.InstitutionQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/institutions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Institutions", description = "Institution Management Endpoints")
public class InstitutionController {
    private final InstitutionCommandService institutionCommandService;
    private final InstitutionQueryService institutionQueryService;

    @Autowired
    public InstitutionController(InstitutionCommandService institutionCommandService, InstitutionQueryService institutionQueryService) {
        this.institutionCommandService = institutionCommandService;
        this.institutionQueryService = institutionQueryService;
    }

    @PostMapping("/register")
    public ResponseEntity<InstitutionDto> createInstitution(@RequestBody InstitutionDto institutionDto) {
        CreateInstitutionCommand command = new CreateInstitutionCommand(
                institutionDto.name(),
                new Address(institutionDto.street(), institutionDto.city(), institutionDto.state())
        );
        EducationalInstitution institution = institutionCommandService.handle(command);
        return ResponseEntity.ok(InstitutionMapper.toDto(institution));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionDto> getInstitutionById(@PathVariable Long id) {
        Optional<EducationalInstitution> institution = institutionQueryService.handle(new GetInstitutionByIdQuery(id));
        return institution.map(i -> ResponseEntity.ok(InstitutionMapper.toDto(i)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<InstitutionDto>> getAllInstitutions() {
        List<EducationalInstitution> institutions = institutionQueryService.handle(new GetAllInstitutionsQuery());
        List<InstitutionDto> institutionDtos = institutions.stream().map(InstitutionMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(institutionDtos);
    }
}
