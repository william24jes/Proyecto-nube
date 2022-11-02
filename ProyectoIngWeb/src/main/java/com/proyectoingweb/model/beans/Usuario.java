package com.proyectoingweb.model.beans;

public class Usuario {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String dni;
    private String celular;
    private String codigoPucp;
    private String correoPucp;
    private String categoria;
    private String rol;
    private String contrasena;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCodigoPucp() {
        return codigoPucp;
    }

    public void setCodigoPucp(String codigoPucp) {
        this.codigoPucp = codigoPucp;
    }

    public String getCorreoPucp() {
        return correoPucp;
    }

    public void setCorreoPucp(String correoPucp) {
        this.correoPucp = correoPucp;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
