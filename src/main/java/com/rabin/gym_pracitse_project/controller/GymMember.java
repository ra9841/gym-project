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
        return ResponseEntity.ok(gymMemberService.savingMemberDetails(gymMemberDto));
    }

    @GetMapping
    public ResponseEntity<List<GymMemberDto>> listOfGymMember() {
        return ResponseEntity.ok(gymMemberService.allListOfGymMember());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletingTheRecord(@PathVariable("id") Long id) {
        return ResponseEntity.ok(gymMemberService.deleteTheRecordById(id));

    }

    @GetMapping("/shows/{email}")
    public ResponseEntity<GymMemberDto> getRecordOnTheBasisOfEmail(@PathVariable String email) {
        return ResponseEntity.ok(gymMemberService.getParticularRecordByEmail(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<GymMemberDto> updateRecordOnTheBasisOfEmail(@RequestBody GymMemberDto gymMemberDto, @PathVariable String email) {
        return ResponseEntity.ok(gymMemberService.updatingRecordByEmail(gymMemberDto, email));
    }


}
