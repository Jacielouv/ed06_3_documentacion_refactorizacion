package org.ed06.model;

import static org.ed06.model.Validacion.*;

public class Cliente {
    public int id;
    public String nombre;
    public String dni;
    public String email;
    public boolean esVip;

    public Cliente(int id, String nombre, String dni, String email, boolean esVip) {
        this.id = id;
        this.esVip = esVip;

        // Validación de nombre, DNI y email
        if(validarNombre(nombre)) {this.nombre = nombre;}
        if(validarDni(dni)) {this.dni = dni;}
        if(validarEmail(email)) {this.email = email;}
    }

    // Métodos getter y setter
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public boolean isVip() {return esVip;}
    public void setVip(boolean esVip) {this.esVip = esVip;}


}