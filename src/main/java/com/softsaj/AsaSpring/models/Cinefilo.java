/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.AsaSpring.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Marcos
 */
@Entity
public class Cinefilo implements Serializable {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)    
private int id;
@Column(nullable = false)
private String user;
@Column(nullable = false)
private String password;
@Column(nullable = false)
private String email;
@Column(nullable = false)
private String nome;
private String telefone;
private String idade;
private String foto;

    public Cinefilo() {
        super();
    }

    public Cinefilo(int id, String user, String password, String email, String nome, String telefone, String idade, String foto) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cinefilo other = (Cinefilo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

}