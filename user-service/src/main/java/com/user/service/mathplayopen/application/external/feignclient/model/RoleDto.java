package com.user.service.mathplayopen.application.external.feignclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RoleDto fromEnum(Roles role) {
        return new RoleDto(null, role.name());
    }
}
