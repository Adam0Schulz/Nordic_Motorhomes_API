package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.GeneralEntity;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralService <T extends GeneralEntity> {

    //Dependencies
    protected final GeneralRepository<T> repo;

    public GeneralService(GeneralRepository<T> repo) {
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
    public Optional<T> update(Long id, T entity) {
        entity.setID(id);
        return !(repo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(entity));
    }

    // Delete an entity
    public void delete(Long id) { repo.deleteById(id); }



}
