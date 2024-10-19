package com.user.service.mathplayopen.domain.model.events;

public record InstructorAssignedToStudentEvent(Long studentId, Long instructorId) {
}
