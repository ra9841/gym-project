package com.rabin.gym_pracitse_project.service;

import com.rabin.gym_pracitse_project.dto.GymMemberDto;
import com.rabin.gym_pracitse_project.entity.GymMemberEntity;
import com.rabin.gym_pracitse_project.exception.gymMemberEmailAlreadyExistException;
import com.rabin.gym_pracitse_project.repository.GymMemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GymMemberServiceImpl implements GymMemberService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Autowired
    private GymMemberRepository gymMemberRepository;

    @Override
    public GymMemberDto savingMemberDetails(GymMemberDto gymMemberDto) {
        Optional<GymMemberEntity> existMember = gymMemberRepository.findByEmail(gymMemberDto.getEmail());
        if (existMember.isPresent()) {
            throw new gymMemberEmailAlreadyExistException("Email is already exist ");
        }

        GymMemberEntity gymMemberEntity = new GymMemberEntity();
        gymMemberEntity.setName(gymMemberDto.getName().toLowerCase());
        gymMemberEntity.setAddress(gymMemberDto.getAddress().toLowerCase());
        gymMemberEntity.setEmail(gymMemberDto.getEmail().toLowerCase());
        gymMemberEntity.setRole(gymMemberDto.getRole().toLowerCase());
        gymMemberEntity.setCreateDate(LocalDateTime.now());
        gymMemberEntity.setModifiedDate(LocalDateTime.now());
        gymMemberEntity.setPassword(gymMemberDto.getPassword());
        gymMemberEntity.setDateOfBirth(gymMemberDto.getDateOfBirth());

        GymMemberEntity gymMemberEntity1 = gymMemberRepository.save(gymMemberEntity);

        GymMemberDto gymMemberDto1 = new GymMemberDto();
        BeanUtils.copyProperties(gymMemberEntity1, gymMemberDto1);

        return gymMemberDto1;
    }

    @Override
    public List<GymMemberDto> allListOfGymMember() {
        List<GymMemberEntity> gymMemberEntities = gymMemberRepository.findAll();
        return gymMemberEntities.stream().map(gymMemberEntity -> {
            GymMemberDto gymMemberDto = new GymMemberDto();
            BeanUtils.copyProperties(gymMemberEntity, gymMemberDto);
            return gymMemberDto;
        }).toList();
    }


}
