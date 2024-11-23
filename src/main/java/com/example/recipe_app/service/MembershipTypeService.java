package com.example.recipe_app.service;

import com.example.recipe_app.entity.MembershipType;
import com.example.recipe_app.repository.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipTypeService {

    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    public List<MembershipType> getAllMembershipTypes() {
        return membershipTypeRepository.findAll();
    }

    public Optional<MembershipType> getMembershipTypeById(Integer id) {
        return membershipTypeRepository.findById(id);
    }

    public MembershipType saveMembershipType(MembershipType membershipType) {
        return membershipTypeRepository.save(membershipType);
    }

    public void deleteMembershipType(Integer id) {
        membershipTypeRepository.deleteById(id);
    }
} 