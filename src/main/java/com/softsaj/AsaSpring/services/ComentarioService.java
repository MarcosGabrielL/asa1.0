/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.AsaSpring.services;

import com.softsaj.AsaSpring.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcos
 */
@Service
public class ComentarioService {
    
    @Autowired
    private ComentarioRepository rp;
}
