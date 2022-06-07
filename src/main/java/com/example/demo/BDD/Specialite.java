package com.example.demo.BDD;


import javax.persistence.*;
import java.util.List;


@Entity
public class Specialite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IdSpecialite;
    private String NomSpecialite;
    @OneToMany
    private List<Etudiant> Etudiants;
    @OneToMany
    private List<Matiere> Matieres;

    public Specialite() {
    }

    public Long getIdSpecialite() {
        return IdSpecialite;
    }

    public void setIdSpecialite(Long idSpecialite) {
        IdSpecialite = idSpecialite;
    }

    public String getNomSpecialite() {
        return NomSpecialite;
    }

    public void setNomSpecialite(String nomSpecialite) {
        NomSpecialite = nomSpecialite;
    }

    public List<Etudiant> getEtudiants() {
        return Etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        Etudiants = etudiants;
    }

    public List<Matiere> getMatieres() {
        return Matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        Matieres = matieres;
    }
}
