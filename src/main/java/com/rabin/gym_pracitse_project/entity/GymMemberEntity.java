package com.rabin.gym_pracitse_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "GymMember_tbl")
public class GymMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gymMember_id", nullable = false)
    private Long id;
    @Column(name = "gymMember_name", nullable = false)
    private String name;
    @Column(name = "gymMember_role", nullable = false)
    private String role;
    @Column(name = "gymMember_address", nullable = false)
    private String address;
    @Column(name = "gymMember_username", nullable = false)
    private String email;
    @Column(name = "gymMember_password", nullable = false)
    private String password;
    @Column(name = "gymMember_dateOfBirth", nullable = false)
    private String dateOfBirth;
    @Column(name = "gymMember_createDate", nullable = false)
    private LocalDateTime createDate;
    @Column(name = "gymMember_modifiedDate", nullable = false)
    private LocalDateTime modifiedDate;


    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
    }


}
