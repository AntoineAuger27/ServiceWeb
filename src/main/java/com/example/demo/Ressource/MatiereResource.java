package com.example.demo.Ressource;
import com.example.demo.BDD.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Path("matiere")
public class MatiereResource {
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
    public Matiere createMatiere(Matiere m) {
        return matiereRepository.save(m);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Matiere> getAllMatiere() {
        List<Matiere> matieres = new ArrayList<>();
        matiereRepository.findAll().forEach(matieres::add);
        return matieres;
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMatiere(@PathParam("id") Long id) {
        if (matiereRepository.findById(id).isPresent()) {
            matiereRepository.deleteById(id);
        }
        return Response.noContent().build();
    }
}
