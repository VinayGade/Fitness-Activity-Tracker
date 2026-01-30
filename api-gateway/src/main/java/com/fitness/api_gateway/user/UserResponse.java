package com.fitness.api_gateway.user;

import lombok.*;

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
