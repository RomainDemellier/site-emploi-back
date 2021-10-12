package com.oc.projets.siteemploiback.controller;

import com.oc.projets.siteemploiback.dto.CritereDTO;
import com.oc.projets.siteemploiback.exception.ResourceNotFoundException;
import com.oc.projets.siteemploiback.service.CritereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/criteres")
public class CritereController {

    @Autowired
    private CritereService critereService;

    @PostMapping("")
    public ResponseEntity create(@RequestBody CritereDTO critereDTO) {
        try {
            critereDTO = this.critereService.create(critereDTO);
            return ResponseEntity.ok(critereDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id) {
        try {
            CritereDTO critereDTO = this.critereService.findByIdDTO(id);
            return ResponseEntity.ok(critereDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity getAll() {
        try {
            List<CritereDTO> critereDTOList = this.critereService.getAll();
            return ResponseEntity.ok(critereDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody CritereDTO critereDTO) {
        try {
            critereDTO.setId(id);
            critereDTO = this.critereService.update(critereDTO);
            return ResponseEntity.ok(critereDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try {
            this.critereService.deleteById(id);
            return ResponseEntity.ok("Critere supprimé avec succès id :" + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
