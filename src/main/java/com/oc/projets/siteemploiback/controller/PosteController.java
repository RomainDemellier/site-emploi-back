package com.oc.projets.siteemploiback.controller;

import com.oc.projets.siteemploiback.dto.PosteDTO;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.service.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/postes")
public class PosteController {

    @Autowired
    private PosteService posteService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody PosteDTO posteDTO) {
        try {
            posteDTO = this.posteService.create(posteDTO);
            return ResponseEntity.ok(posteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity getAll() {
        List<PosteDTO> posteDTOList = this.posteService.getAll();
        return ResponseEntity.ok(posteDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id) {
        try {
            PosteDTO posteDTO = this.posteService.findByIdDTO(id);
            return ResponseEntity.ok(posteDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody PosteDTO posteDTO) {
        try {
            posteDTO.setId(id);
            posteDTO = this.posteService.update(posteDTO);
            return ResponseEntity.ok(posteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") Long id) {
        try {
            this.posteService.deleteById(id);
            return ResponseEntity.ok("Poste supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/addTag")
    public ResponseEntity addTag(@PathVariable(value = "id") Long id, @RequestBody String tag) {
        try {
            PosteDTO posteDTO = this.posteService.addTag(id,tag);
            return ResponseEntity.ok(posteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/addTags")
    public ResponseEntity addTags(@PathVariable(value = "id") Long id, @RequestBody List<String> tags) {
        try {
            PosteDTO posteDTO = this.posteService.mergeTags(id,tags);
            return ResponseEntity.ok(posteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/deleteTag")
    public ResponseEntity deleteTag(@PathVariable(value = "id") Long id, @RequestBody String tag) {
        try {
            PosteDTO posteDTO = this.posteService.deleteTag(id,tag);
            return ResponseEntity.ok(posteDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
