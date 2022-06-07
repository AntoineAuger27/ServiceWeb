package com.example.demo.BDD;
import com.example.demo.BDD.Note;

import javax.persistence.*;
import java.util.List;

@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IdEtudiant;
    private String NomEtudiant;
    private String PrenomEtudiant;
    private int Age;
    @OneToMany
    private List<Note> Notes;

    public Etudiant() {
    }

    public Long getIdEtudiant() {
        return IdEtudiant;
    }

    public void setIdEtudiant(Long idEtudiant) {
        IdEtudiant = idEtudiant;
    }

    public String getNomEtudiant() {
        return NomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        NomEtudiant = nomEtudiant;
    }

    public String getPrenomEtuduiant() {
        return PrenomEtudiant;
    }

    public void setPrenomEtuduiant(String prenomEtuduiant) {
        PrenomEtudiant = prenomEtuduiant;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public List<Note> getNotes() {
        return Notes;
    }

    public void setNotes(List<Note> notes) {
        Notes = notes;
    }
}
