package com.example.demo.BDD;
import javax.persistence.*;
import java.util.List;


@Entity
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IdProfesseur;
    private String NomProfesseur;
    private String PrenomProfesseur;
    @OneToMany
    private List<Matiere> Matieres;

    public Professeur() {
    }

    public Long getIdProfesseur() {
        return IdProfesseur;
    }

    public void setIdProfesseur(Long idProfesseur) {
        IdProfesseur = idProfesseur;
    }

    public String getNomProfesseur() {
        return NomProfesseur;
    }

    public void setNomProfesseur(String nomProfesseur) {
        NomProfesseur = nomProfesseur;
    }

    public String getPrenomProfesseur() {
        return PrenomProfesseur;
    }

    public void setPrenomProfesseur(String prenomProfesseur) {
        PrenomProfesseur = prenomProfesseur;
    }

    public List<Matiere> getMatieres() {
        return Matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        Matieres = matieres;
    }
}
