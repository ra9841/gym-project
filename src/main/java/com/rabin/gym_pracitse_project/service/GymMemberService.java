package com.rabin.gym_pracitse_project.service;

import com.rabin.gym_pracitse_project.dto.GymMemberDto;

import java.util.List;

public interface GymMemberService {
    GymMemberDto savingMemberDetails(GymMemberDto gymMemberDto);

    List<GymMemberDto> allListOfGymMember();


    String deleteTheRecordById(Long id);

    GymMemberDto getParticularRecordByEmail(String email);

    GymMemberDto updatingRecordByEmail(GymMemberDto gymMemberDto, String email);


}
