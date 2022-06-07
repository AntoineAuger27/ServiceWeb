package com.example.demo.Ressource;
import com.example.demo.BDD.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Path("note")
public class NoteResource {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private SpecialiteRepository specialiteRepository;
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private ProfesseurRepository professeurRepository;
}
