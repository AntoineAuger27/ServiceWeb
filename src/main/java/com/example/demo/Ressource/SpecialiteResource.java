package com.example.demo.Ressource;
import com.example.demo.BDD.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Path("specialite")
public class SpecialiteResource {
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
    public Specialite createSpecialite(Specialite s) {
        return specialiteRepository.save(s);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Specialite> getAllSpecialite() {
        List<Specialite> specialites = new ArrayList<>();
        specialiteRepository.findAll().forEach(specialites::add);
        return specialites;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Specialite updateTotalySpecialite(@PathParam("id") Long id, Specialite s) {
        s.setIdSpecialite(id);
        return specialiteRepository.save(s);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSpecialite(@PathParam("id") Long id) {
        if (specialiteRepository.findById(id).isPresent()) {
            specialiteRepository.deleteById(id);
        }
        return Response.noContent().build();
    }


    //Ajouter un etudiant dans une spécialité
    @PATCH
    @Path("{idSpecialite}/{idEtudiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void ajouteEtudiant(@PathParam("idSpecialite") Long idSpecialite, @PathParam("idEtudiant") Long idEtudiant) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).get();
        specialiteRepository.findById(idSpecialite).get().getEtudiants().add(etudiant);
    }


    //Recupere la liste des etudiants par specialite identifié par id
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> listerEtudiants(@PathParam("id") Long id) {
        return specialiteRepository.findById(id).get().getEtudiants();
    }
}
