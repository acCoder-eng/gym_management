package com.example.recipe_app.controller;

import com.example.recipe_app.entity.Reservation;
import com.example.recipe_app.entity.Class;
import com.example.recipe_app.entity.Member;
import com.example.recipe_app.service.ReservationService;
import com.example.recipe_app.repository.ClassRepository;
import com.example.recipe_app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        if (reservation.getClassEntity() == null || reservation.getClassEntity().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Class> classEntity = classRepository.findById(reservation.getClassEntity().getId());
        if (!classEntity.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        if (reservation.getMember() == null || reservation.getMember().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Member> member = memberRepository.findById(reservation.getMember().getId());
        if (!member.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        reservation.setClassEntity(classEntity.get());
        reservation.setMember(member.get());
        Reservation savedReservation = reservationService.saveReservation(reservation);
        return ResponseEntity.ok(savedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
} 