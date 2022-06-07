package com.example.demo.BDD;
import javax.persistence.*;
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IdNote;
    private Long Valeur;

    public Note() {
    }

    public Long getIdNote() {
        return IdNote;
    }

    public void setIdNote(Long idNote) {
        IdNote = idNote;
    }

    public Long getValeur() {
        return Valeur;
    }

    public void setValeur(Long valeur) {
        Valeur = valeur;
    }

}
