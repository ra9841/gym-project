package com.rabin.gym_pracitse_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GymMemberDto {
    private Long id;
    private String name;
    private String role;
    private String address;
    private String email;
    private String password;
    private String dateOfBirth;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

}
