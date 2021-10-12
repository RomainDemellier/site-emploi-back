package com.oc.projets.siteemploiback.controller;

import com.oc.projets.siteemploiback.dto.UtilisateurDTO;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.exception.UtilisateurException;
import com.oc.projets.siteemploiback.service.UtilisateurConnecteService;
import com.oc.projets.siteemploiback.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private UtilisateurConnecteService utilisateurConnecteService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody UtilisateurDTO utilisateurDTO) {
        try {
            utilisateurDTO = this.utilisateurService.create(utilisateurDTO);
            return ResponseEntity.ok(utilisateurDTO);
        } catch (UtilisateurException e) {
            System.out.println("erreur : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            System.out.println("erreur : " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity getAll() {
        List<UtilisateurDTO> utilisateurDTOList = this.utilisateurService.getAllDTO();
        return ResponseEntity.ok(utilisateurDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id) {
        try {
            UtilisateurDTO utilisateurDTO = this.utilisateurService.findByIdDTO(id);
            return ResponseEntity.ok(utilisateurDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody UtilisateurDTO utilisateurDTO) {
        try {
            utilisateurDTO.setId(id);
            utilisateurDTO = this.utilisateurService.update(utilisateurDTO);
            return ResponseEntity.ok(utilisateurDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try {
            this.utilisateurService.deleteById(id);
            return ResponseEntity.ok("Utilisateur supprimé avec succès.");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/utilisateurConnecte")
    public ResponseEntity getUtilisateurConnecte() {
        try {
            UtilisateurDTO utilisateurDTO = this.utilisateurConnecteService.getUtilisateurDTOConnecte();
            return ResponseEntity.ok(utilisateurDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
