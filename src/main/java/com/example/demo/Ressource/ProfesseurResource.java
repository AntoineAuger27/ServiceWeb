package com.example.demo.Ressource;
import com.example.demo.BDD.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Path("professeur")
public class ProfesseurResource {
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Professeur createProfesseur(Professeur p) {
        return professeurRepository.save(p);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professeur> getAllProfesseur() {
        List<Professeur> professeurs = new ArrayList<>();
        professeurRepository.findAll().forEach(professeurs::add);
        return professeurs;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Professeur updateTotalyProfesseur(@PathParam("id") Long id, Professeur p) {
        p.setIdProfesseur(id);
        return professeurRepository.save(p);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfesseur(@PathParam("id") Long id) {
        if (professeurRepository.findById(id).isPresent()) {
            professeurRepository.deleteById(id);
        }
        return Response.noContent().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfesseurById(@PathParam("id") Long id) {
        Optional<Professeur> p = professeurRepository.findById(id);
        if (p.isPresent()) {
            return Response.ok(p.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //Recupérer le professeur selon le nom et prenom
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfesseurByNomAndPrenom(@QueryParam("nom") String nom, @QueryParam("prenom") String prenom) {
        Optional<Professeur> p = professeurRepository.trouverProfesseurParNomEtPrenom(nom, prenom);
        if (p.isPresent()) {
            return Response.ok(p.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //Recupérer la liste des matieres que le professeur enseigne
    @GET
    @Path("{id}/matieres")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Matiere> listerMatiere(@PathParam("id") Long id) {
        return professeurRepository.findById(id).get().getMatieres();
    }
}
