package com.example.demo.BDD;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfesseurRepository extends CrudRepository<Professeur, Long> {
    //On defini la recherche par nom et prenom
    //findById deja defini
    //findAll deja defini
    @Query("select p from Professeur p where p.NomProfesseur = :nom AND p.PrenomProfesseur = :prenom")
    Optional<Professeur> trouverProfesseurParNomEtPrenom(@Param("nom") String nom, @Param("prenom") String prenom);
}