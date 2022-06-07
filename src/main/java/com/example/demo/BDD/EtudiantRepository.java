package com.example.demo.BDD;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends CrudRepository<Etudiant, Long> {
    //On defini la recherche par nom et prenom
    //findById deja defini
    //findAll deja defini
    @Query("select e from Etudiant e where e.NomEtudiant = :nom AND e.PrenomEtudiant = :prenom")
    Optional<Etudiant> trouverEtudiantParNomEtPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

}