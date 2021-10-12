package com.oc.projets.siteemploiback.controller;

import com.oc.projets.siteemploiback.conversion_dto_entity.ConversionUtilisateur;
import com.oc.projets.siteemploiback.dto.UtilisateurDTO;
import com.oc.projets.siteemploiback.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin/utilisateurs")
public class AdminUtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ConversionUtilisateur conversionUtilisateur;

    @PostMapping("")
    public ResponseEntity create(@RequestBody UtilisateurDTO utilisateurAdminDTO) {
        try {
            utilisateurAdminDTO = this.utilisateurService.createAdmin(utilisateurAdminDTO);
            return ResponseEntity.ok(utilisateurAdminDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
