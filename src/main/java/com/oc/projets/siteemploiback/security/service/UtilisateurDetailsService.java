package com.oc.projets.siteemploiback.security.service;

import com.oc.projets.siteemploiback.entity.Utilisateur;
import com.oc.projets.siteemploiback.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = this.utilisateurRepository.findByEmail(email);
        GrantedAuthority authority = new SimpleGrantedAuthority((utilisateur.getRole()));
        UserDetails userDetails = new User(utilisateur.getEmail(), utilisateur.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
