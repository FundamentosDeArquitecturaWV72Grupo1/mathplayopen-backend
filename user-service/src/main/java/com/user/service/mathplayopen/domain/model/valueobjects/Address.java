package com.user.service.mathplayopen.domain.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public record Address(String street, String city, String state) {
    public Address {
        if (street == null || city == null || state == null) {
            throw new IllegalArgumentException("Address fields cannot be null");
        }
    }
}
