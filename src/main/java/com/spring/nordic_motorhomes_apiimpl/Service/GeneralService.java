package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Entity;
import com.spring.nordic_motorhomes_apiimpl.Entity.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class GeneralService<T, R extends JpaRepository<T,Long>> {

    private final R repo;

    @Autowired
    public GeneralService(R repo) {
        this.repo = repo;
    }

    // Save an entity
    public T save(T entity) {
        return  repo.save(entity);
    }

    // Get an entity
    public Optional<T> get(Long id) {
        return repo.findById(id);
    }

    // Get all entities
    public List<T> getAll() {
        return repo.findAll();
    }

    // Update an entity
    /*public Optional<T> update(Long id, T entity) {
        try {
            entity.setID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return !(repo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(entity));
    }*/

    // Delete an entity
    public void delete(Long id) { repo.deleteById(id); }



}
