package com.softsaj.AsaSpring.models;


import java.util.Objects;


//Entity
//Table(name = "cinefilos")
public class Cinefilo {
     
    //Id
    //GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     //Column(nullable = true, unique = true, length = 45)
    private String usuario; 
     
    //Column(nullable = true, unique = true, length = 45)
    private String email;
     
    //Column(nullable = true, length = 64)
    private String password;
     
    //Column(name = "first_name", nullable = true, length = 20)
    private String firstName;
     
    //Column(name = "last_name", nullable = true, length = 20)
    private String lastName;
     
     private String nome;
     private String telefone;
     private String idade;
     private String foto;

    public Cinefilo() {
        super();
    }

    public Cinefilo(Long id, String email, String password, String firstName, String lastName,  String nome, String telefone, String idade, String foto, String usuario) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
          this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.foto = foto;
          this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

      public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    //Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    //Override
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
     
    
     
}

