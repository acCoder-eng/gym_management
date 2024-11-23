package com.example.recipe_app.controller;

import com.example.recipe_app.entity.Trainer;
import com.example.recipe_app.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Integer id) {
        return trainerService.getTrainerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
        if (trainer.getEmail() == null || trainer.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Trainer savedTrainer = trainerService.saveTrainer(trainer);
        return ResponseEntity.ok(savedTrainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Integer id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
