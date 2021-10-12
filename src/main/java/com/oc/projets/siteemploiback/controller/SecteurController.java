package com.oc.projets.siteemploiback.controller;

import com.oc.projets.siteemploiback.dto.PosteDTO;
import com.oc.projets.siteemploiback.dto.SecteurDTO;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.service.SecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/secteurs")
public class SecteurController {

    @Autowired
    private SecteurService secteurService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody SecteurDTO secteurDTO) {
        try {
            secteurDTO = this.secteurService.create(secteurDTO);
            return ResponseEntity.ok(secteurDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity getAll() {
        List<SecteurDTO> secteurDTOList = this.secteurService.getAll();
        return ResponseEntity.ok(secteurDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id) {
        try {
            SecteurDTO secteurDTO = this.secteurService.findByIdDTO(id);
            return ResponseEntity.ok(secteurDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id,@RequestBody SecteurDTO secteurDTO) {
        try {
            secteurDTO.setId(id);
            secteurDTO = this.secteurService.update(secteurDTO);
            return ResponseEntity.ok(secteurDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/addPoste")
    public ResponseEntity addPoste(@PathVariable(value = "id") Long id, @RequestBody PosteDTO posteDTO) throws Exception {
        try {
            SecteurDTO secteurDTO = this.secteurService.addPoste(id,posteDTO);
            return ResponseEntity.ok(secteurDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/deletePoste/{posteId}")
    public ResponseEntity deletePoste(@PathVariable(value = "id") Long id,@PathVariable(value = "posteId") Long posteId) {
        try {
            SecteurDTO secteurDTO = this.secteurService.deletePoste(id,posteId);
            return ResponseEntity.ok(secteurDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try {
            this.secteurService.deleteById(id);
            return ResponseEntity.ok("Secteur supprimé avec succès id : " + id);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/postes")
    public ResponseEntity getPostes(@PathVariable(value = "id") Long id) {
        try {
            List<PosteDTO> posteDTOList = this.secteurService.getPostesOfSecteur(id);
            return ResponseEntity.ok(posteDTOList);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
