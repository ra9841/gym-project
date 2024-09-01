package com.rabin.gym_pracitse_project.controller;

import com.rabin.gym_pracitse_project.dto.GymMemberDto;
import com.rabin.gym_pracitse_project.service.GymMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym")
public class GymMember {

    @Autowired
    private GymMemberService gymMemberService;

    @PostMapping
    public ResponseEntity<GymMemberDto> registeringGymMember(@RequestBody GymMemberDto gymMemberDto) {
      return  ResponseEntity.ok(gymMemberService.savingMemberDetails(gymMemberDto));
    }

    @GetMapping
    public ResponseEntity<List<GymMemberDto>> listOfGymMember(){
        return ResponseEntity.ok(gymMemberService.allListOfGymMember());
    }

}
