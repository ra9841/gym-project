package com.rabin.gym_pracitse_project.service;

import com.rabin.gym_pracitse_project.dto.GymMemberDto;
import com.rabin.gym_pracitse_project.entity.GymMemberEntity;
import com.rabin.gym_pracitse_project.exception.GymMemberEmailNotExistException;
import com.rabin.gym_pracitse_project.exception.gymMemberEmailAlreadyExistException;

import com.rabin.gym_pracitse_project.repository.GymMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
        gymMemberEntity.setRole(gymMemberDto.getRole().toLowerCase());
        gymMemberEntity.setCreateDate(LocalDateTime.now());
        gymMemberEntity.setModifiedDate(LocalDateTime.now());
        gymMemberEntity.setPassword(gymMemberDto.getPassword());
        gymMemberEntity.setDateOfBirth(gymMemberDto.getDateOfBirth());

        GymMemberEntity gymMemberEntity1 = gymMemberRepository.save(gymMemberEntity);
        log.info("Record save in database {}", gymMemberEntity1);

        GymMemberDto gymMemberDto1 = new GymMemberDto();
        BeanUtils.copyProperties(gymMemberEntity1, gymMemberDto1);
        log.info("Record retrieve from database and sending to frontend {}", gymMemberDto1);

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


    @Override
    public String deleteTheRecordById(Long id) {
        if (gymMemberRepository.existsById(id)) {
            gymMemberRepository.deleteById(id);
            return "delete successfully";
        }
        return "not deleted ";
    }

    @Override
    public GymMemberDto getParticularRecordByEmail(String email) {
        Optional<GymMemberEntity> existGymMember = gymMemberRepository.findByEmail(email);
        if (existGymMember.isPresent()) {
            GymMemberEntity gymMemberEntity = existGymMember.get();
            log.info("Record present in database {}", gymMemberEntity);

            GymMemberDto gymMemberDto = new GymMemberDto();
            BeanUtils.copyProperties(gymMemberEntity, gymMemberDto);
            log.info("Record sending to user from database {}", gymMemberDto);
            return gymMemberDto;
        }
        throw new GymMemberEmailNotExistException("Email is not exist..type with other email");
    }

    @Override
    public GymMemberDto updatingRecordByEmail(GymMemberDto gymMemberDto, String email) {

        GymMemberDto gymMemberDto1 = getParticularRecordByEmail(email);
        gymMemberDto1.setEmail(gymMemberDto.getEmail());
        gymMemberDto1.setName(gymMemberDto.getName());
        gymMemberDto1.setAddress(gymMemberDto.getAddress());
        gymMemberDto1.setDateOfBirth(gymMemberDto.getDateOfBirth());
        gymMemberDto1.setPassword(gymMemberDto.getPassword());
        gymMemberDto1.setRole(gymMemberDto.getRole());


        Optional<GymMemberEntity> existGymMember = gymMemberRepository.findByEmail(email);
        if (existGymMember.isPresent()) {
            GymMemberEntity gymMemberEntity = existGymMember.get();


            gymMemberEntity.setName(gymMemberDto1.getName().toLowerCase());
            gymMemberEntity.setAddress(gymMemberDto1.getAddress().toLowerCase());
            gymMemberEntity.setRole(gymMemberDto1.getRole().toLowerCase());
            gymMemberEntity.setDateOfBirth(gymMemberDto1.getDateOfBirth());
            gymMemberEntity.setPassword(gymMemberDto1.getPassword().toLowerCase());
            gymMemberEntity.setEmail(gymMemberDto1.getEmail().toLowerCase());
            gymMemberEntity.setModifiedDate(LocalDateTime.now());

            GymMemberEntity gymMemberEntity2 = gymMemberRepository.save(gymMemberEntity);

            GymMemberDto gymMemberDto2 = new GymMemberDto();
            BeanUtils.copyProperties(gymMemberEntity2, gymMemberDto2);
            return gymMemberDto2;
        } else {
            throw new GymMemberEmailNotExistException("Email is not exist..type correct one");
        }

    }


}
