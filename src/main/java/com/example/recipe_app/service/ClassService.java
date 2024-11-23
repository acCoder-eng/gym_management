package com.example.recipe_app.service;

import com.example.recipe_app.entity.Class;
import com.example.recipe_app.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Optional<Class> getClassById(Integer id) {
        return classRepository.findById(id);
    }

    public Class saveClass(Class classEntity) {
        return classRepository.save(classEntity);
    }

    public void deleteClass(Integer id) {
        classRepository.deleteById(id);
    }
} 