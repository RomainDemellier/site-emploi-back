package com.oc.projets.siteemploiback.controller;

import com.oc.projets.siteemploiback.dto.AdresseDTO;
import com.oc.projets.siteemploiback.dto.UtilisateurDTO;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.service.RecruteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/recruteurs")
public class RecruteurController {

    @Autowired
    private RecruteurService recruteurService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody UtilisateurDTO utilisateurDTO) {
        System.out.println("dans create recruteurs");
        try {
            utilisateurDTO = this.recruteurService.create(utilisateurDTO);
            return ResponseEntity.ok(utilisateurDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<UtilisateurDTO>> getAll() {
        List<UtilisateurDTO> recruteurDTOList = this.recruteurService.getAll();
        return ResponseEntity.ok(recruteurDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id) {
        try {
            UtilisateurDTO recruteurDTO = this.recruteurService.findByIdDTO(id);
            return ResponseEntity.ok(recruteurDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody UtilisateurDTO recruteurDTO) {
        try {
            recruteurDTO.setId(id);
            recruteurDTO = this.recruteurService.update(recruteurDTO);
            return ResponseEntity.ok(recruteurDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/updateAdresse")
    public ResponseEntity updateAdresseSiege(@RequestBody AdresseDTO adresseDTO) {
        try {
            adresseDTO = this.recruteurService.updateAdresseSiege(adresseDTO);
            return ResponseEntity.ok(adresseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/addAdresseSecondaire")
    public ResponseEntity addAdresseSecondaire(@PathVariable(value = "id") Long id, @RequestBody AdresseDTO adresseDTO) {
        try {
            UtilisateurDTO utilisateurDTO = this.recruteurService.addAdresseSecondaire(id,adresseDTO);
            return ResponseEntity.ok(utilisateurDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try {
            this.recruteurService.deleteById(id);
            return ResponseEntity.ok("Recruteur supprimé avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{recruteurId}/deleteAdresse/{id}")
    public ResponseEntity deleteAdresse(@PathVariable(value = "recruteurId") Long recruteurId, @PathVariable(value = "id") Long id) {
        try {
            UtilisateurDTO recruteurDTO = this.recruteurService.deleteAdresse(recruteurId,id);
            return ResponseEntity.ok(recruteurDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/isRecruteur")
    public ResponseEntity isRecruteur(@PathVariable(value = "id") Long id) {
        try {
            Boolean isRecruteur = this.recruteurService.isRecruteur(id);
            return ResponseEntity.ok(isRecruteur);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
