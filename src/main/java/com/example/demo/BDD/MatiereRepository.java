package com.example.demo.BDD;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MatiereRepository extends CrudRepository<Matiere, Long> {
}