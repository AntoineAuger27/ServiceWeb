package com.example.demo.BDD;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IdMatiere;
    private String NomMatiere;
    @OneToMany
    private List<Note> Notes;


    public Matiere() {
    }

    public Long getIdMatiere() {
        return IdMatiere;
    }

    public void setIdMatiere(Long idMatiere) {
        IdMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return NomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        NomMatiere = nomMatiere;
    }

    public List<Note> getNotes() {
        return Notes;
    }

    public void setNotes(List<Note> notes) {
        Notes = notes;
    }
}
