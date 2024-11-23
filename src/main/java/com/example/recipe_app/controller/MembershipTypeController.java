package com.example.recipe_app.controller;

import com.example.recipe_app.entity.MembershipType;
import com.example.recipe_app.service.MembershipTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membership-types")
public class MembershipTypeController {

    @Autowired
    private MembershipTypeService membershipTypeService;

    @GetMapping
    public List<MembershipType> getAllMembershipTypes() {
        return membershipTypeService.getAllMembershipTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipType> getMembershipTypeById(@PathVariable Integer id) {
        return membershipTypeService.getMembershipTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MembershipType> createMembershipType(@RequestBody MembershipType membershipType) {
        MembershipType savedMembershipType = membershipTypeService.saveMembershipType(membershipType);
        return ResponseEntity.ok(savedMembershipType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembershipType(@PathVariable Integer id) {
        membershipTypeService.deleteMembershipType(id);
        return ResponseEntity.noContent().build();
    }
} 