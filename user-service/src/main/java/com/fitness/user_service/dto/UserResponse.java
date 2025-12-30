package com.fitness.user_service.dto;

import com.fitness.user_service.model.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserResponse {

    private String id;

    private String email;

    private String keycloakId;

    private String password;

    private String firstName;
    private String lastName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
