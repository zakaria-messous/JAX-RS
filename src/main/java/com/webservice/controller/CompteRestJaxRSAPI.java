package com.webservice.controller;

import com.webservice.entitie.Compte;
import com.webservice.repository.CompteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Path("/banque")
public class CompteRestJaxRSAPI {

    @Autowired
    private CompteRepository compteRepository;

    // READ: Retrieve all accounts (supports JSON and XML)
    @Path("/comptes")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Compte> getComptes() {
        // Fetches and returns a list of all accounts from the database
        return compteRepository.findAll();
    }

    // READ: Retrieve an account by its ID (supports JSON and XML)
    @Path("/comptes/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte getCompte(@PathParam("id") Long id) {
        // Fetches and returns an account by ID, or null if not found
        return compteRepository.findById(id).orElse(null);
    }

    // CREATE: Add a new account (supports JSON and XML)
    @Path("/comptes")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte addCompte(Compte compte) {
        // Saves a new account to the database and returns the saved account
        return compteRepository.save(compte);
    }

    // UPDATE: Update an existing account (supports JSON and XML)
    @Path("/comptes/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte updateCompte(@PathParam("id") Long id, Compte compte) {
        // Finds the existing account by ID
        Compte existingCompte = compteRepository.findById(id).orElse(null);
        if (existingCompte != null) {
            // Updates the properties of the existing account
            existingCompte.setSolde(compte.getSolde());
            existingCompte.setDateCreation(compte.getDateCreation());
            existingCompte.setTypeCompte(compte.getTypeCompte());
            // Saves and returns the updated account
            return compteRepository.save(existingCompte);
        }
        return null; // Returns null if the account was not found
    }
}
