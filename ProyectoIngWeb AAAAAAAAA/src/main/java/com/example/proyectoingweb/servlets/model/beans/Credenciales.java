package com.example.proyectoingweb.servlets.model.beans;

public class Credenciales {

    private String idUsuario;
    private String correoPucp;
    private String codigoPucp;
    private String contrasenaHasheada;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoPucp() {
        return correoPucp;
    }

    public void setCorreoPucp(String correoPucp) {
        this.correoPucp = correoPucp;
    }

    public String getCodigoPucp() {
        return codigoPucp;
    }

    public void setCodigoPucp(String codigoPucp) {
        this.codigoPucp = codigoPucp;
    }

    public String getContrasenaHasheada() {
        return contrasenaHasheada;
    }

    public void setContrasenaHasheada(String contrasenaHasheada) {
        this.contrasenaHasheada = contrasenaHasheada;
    }
}
