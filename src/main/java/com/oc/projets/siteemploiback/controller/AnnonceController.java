package com.oc.projets.siteemploiback.controller;

import com.oc.projets.siteemploiback.dto.AnnonceDTO;
import com.oc.projets.siteemploiback.dto.UtilisateurDTO;
import com.oc.projets.siteemploiback.exception.AnnonceException;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/annonces")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody AnnonceDTO annonceDTO) {
        try {
            annonceDTO = this.annonceService.create(annonceDTO);
            return ResponseEntity.ok(annonceDTO);
        } catch (AnnonceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity getAll() {
        try {
            List<AnnonceDTO> annonceDTOList = this.annonceService.getAll();
            return ResponseEntity.ok(annonceDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id) {
        try {
            AnnonceDTO annonceDTO = this.annonceService.findByIdDTO(id);
            return ResponseEntity.ok(annonceDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody AnnonceDTO annonceDTO) {
        try {
            annonceDTO.setId(id);
            annonceDTO = this.annonceService.updateDTO(annonceDTO);
            return ResponseEntity.ok(annonceDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/publie")
    public ResponseEntity publier(@PathVariable(value = "id") Long id) {
        try {
            AnnonceDTO annonceDTO = this.annonceService.publier(id);
            return ResponseEntity.ok(annonceDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/depublie")
    public ResponseEntity depublier(@PathVariable(value = "id") Long id) {
        try {
            AnnonceDTO annonceDTO = this.annonceService.depublier(id);
            return ResponseEntity.ok(annonceDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/addOneUtilisateur")
    public ResponseEntity addOneUtilisateur(@PathVariable(value = "id") Long id, @RequestBody UtilisateurDTO utilisateurDTO) {
        try {
            AnnonceDTO annonceDTO = this.annonceService.findAndAddOneUtilisateur(id,utilisateurDTO);
            return ResponseEntity.ok(annonceDTO);
        } catch (AnnonceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/addSeveralUtilisateur")
    public ResponseEntity addSeveralUtilisateur(@PathVariable(value = "id") Long id, @RequestBody List<UtilisateurDTO> utilisateurListDTO) {
        try {
            AnnonceDTO annonceDTO = this.annonceService.findAndAddSeveralUtilisateur(id,utilisateurListDTO);
            return ResponseEntity.ok(annonceDTO);
        } catch (AnnonceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try {
            this.annonceService.deleteById(id);
            return ResponseEntity.ok("Annonce supprimée avec succès id : " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/annoncesNotRead/{utilisateurId}")
    public ResponseEntity getAnnoncesNotRead(@PathVariable(value = "utilisateurId") Long id) {
        try {
            List<AnnonceDTO> annonceDTOList = this.annonceService.findAnnonceNotReadByUtilisateurDTOById(id);
            return ResponseEntity.ok(annonceDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/emptyListUtilisateurs")
    public ResponseEntity emptyListUtilisateurs(@PathVariable(value = "id") Long id) {
        try {
            AnnonceDTO annonceDTO = this.annonceService.emptyListUtilisateurs(id);
            return ResponseEntity.ok(annonceDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
