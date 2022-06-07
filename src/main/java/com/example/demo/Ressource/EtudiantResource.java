package com.example.demo.Ressource;
import com.example.demo.BDD.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Path("etudiant")
public class EtudiantResource {
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
    public Etudiant createEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> getAllEtudiant() {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiantRepository.findAll().forEach(etudiants::add);
        return etudiants;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Etudiant updateTotalyEtudiant(@PathParam("id") Long id, Etudiant e) {
        e.setIdEtudiant(id);
        return etudiantRepository.save(e);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEtudiant(@PathParam("id") Long id) {
        if (etudiantRepository.findById(id).isPresent()) {
            etudiantRepository.deleteById(id);
        }
        return Response.noContent().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantById(@PathParam("id") Long id) {
        Optional<Etudiant> p = etudiantRepository.findById(id);
        if (p.isPresent()) {
            return Response.ok(p.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //Recupérer l'etudiant selon le nom et prenom
    //URI: http://localhost:8080/api/etudiant?nom=NomEtudiant&prenom=PrenomEtudiant
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantByNomAndPrenom(@QueryParam("nom") String nom, @QueryParam("prenom") String prenom) {
        Optional<Etudiant> p = etudiantRepository.trouverEtudiantParNomEtPrenom(nom, prenom);
        if (p.isPresent()) {
            return Response.ok(p.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateAge(@PathParam("id") Long id, Etudiant e) {
        int age = e.getAge();
        Optional<Etudiant> optional = etudiantRepository.findById(id);

        if (optional.isPresent()) {
            Etudiant eBDD = optional.get();
            eBDD.setAge(age);
            etudiantRepository.save(eBDD);
            return Response.ok(eBDD).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //Ajouter une note à un etudiant
    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Etudiant ajouteNote(@PathParam("id") Long id, Etudiant e, @QueryParam("note") Note note) {
        e.getNotes().add(note);
        return etudiantRepository.save(e);
    }

    //Recupere les notes d'un etudiant identifié par id
    @GET
    @Path("{id}/{notes}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Note> listerNotes(@PathParam("id") Long id) {
        return etudiantRepository.findById(id).get().getNotes();
    }


}
