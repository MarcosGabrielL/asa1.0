/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.AsaSpring.security;

/**
 *
 * @author Marcos
 */
import com.softsaj.AsaSpring.models.Cinefilo;
import com.softsaj.AsaSpring.models.Torrent;
import com.softsaj.AsaSpring.security.AuthRequest;
import com.softsaj.AsaSpring.security.User;
import com.softsaj.AsaSpring.security.JwtUtil;
import com.softsaj.AsaSpring.services.SearchMoviesTorrent;
import com.softsaj.AsaSpring.util.convertHtmlIntoData;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppController {
    
   
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

	
    @GetMapping("")
    public String viewHomePage() {
    	
        return "index";
    }

	
     @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
              
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
    //authRequest.setPassword(encodedPassword);
      //  System.out.println("Senha: "+authRequest.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signupform";
    }

@PostMapping("/process_register")
public ResponseEntity<?> processRegister(@RequestBody User user) {
   // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //String encodedPassword = passwordEncoder.encode(user.getPassword());
    //user.setPassword(encodedPassword);
     
  //  User newUser = userRepo.save(user);
    
     //Cria Cinefilo
    Cinefilo cinefilo = new Cinefilo();
    cinefilo.setEmail(user.getEmail());
    cinefilo.setId(user.getId());
    cinefilo.setUsuario(user.getFirstName());
    cinefilo.setNome(user.getFirstName()+" "+user.getLastName());
  //  vs.addCinefilo(cinefilo);
    
     
    return new ResponseEntity<>("newUser", HttpStatus.CREATED);
}

    @GetMapping("/users")
    public String listUsers(Model model) {
   // List<User> listUsers = userRepo.findAll();
    //model.addAttribute("listUsers", listUsers);
     
    
    return "users";
}

 @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail (@PathVariable("email") String email) {
       // User user = userRepo.findByEmail(email);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
 
 @GetMapping("/movies/torrents/{query}/{query_en}/{type}/{seasons}")
 public ResponseEntity<?> getTorrentsMovie (@PathVariable("query") String query,
		 									@PathVariable("query_en") String query_en,
		 									@PathVariable("type") String tipo,
		 									@PathVariable("seasons") String seasons) {
    int Codido_Category_Movie = 201;
     return new ResponseEntity<>(new SearchMoviesTorrent().SearchTorrents(seasons, tipo, query, query_en, Codido_Category_Movie), HttpStatus.OK);
 }

}
