package com.example.proyectoingweb.servlets.model.beans;

public class Incidencias {
    private int idIncidencia;
    private int idUsuarioQueCreoIncidencia;
    private Usuarios usuario;
    private Usuarios seguridad;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String ubicacion;
    private String foto;
    private int destacado;
    private String datetime;
    private int anonimo;
    private String urgencia;
    private String estadoIncidencia;
    private int numEstrellas;
    private String nombreUsuarioQueDestaco;
    private ZonaPucp zonaPucp;

    public int getIdUsuarioQueCreoIncidencia() {
        return idUsuarioQueCreoIncidencia;
    }

    public void setIdUsuarioQueCreoIncidencia(int idUsuarioQueCreoIncidencia) {
        this.idUsuarioQueCreoIncidencia = idUsuarioQueCreoIncidencia;
    }

    public String getNombreUsuarioQueDestaco() {
        return nombreUsuarioQueDestaco;
    }

    public void setNombreUsuarioQueDestaco(String nombreUsuarioQueDestaco) {
        this.nombreUsuarioQueDestaco = nombreUsuarioQueDestaco;
    }

    public Usuarios getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Usuarios seguridad) {
        this.seguridad = seguridad;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getDestacado() {
        return destacado;
    }

    public void setDestacado(int destacado) {
        this.destacado = destacado;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getAnonimo() {
        return anonimo;
    }

    public void setAnonimo(int anonimo) {
        this.anonimo = anonimo;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    public String getEstadoIncidencia() {
        return estadoIncidencia;
    }

    public void setEstadoIncidencia(String estadoIncidencia) {
        this.estadoIncidencia = estadoIncidencia;
    }

    public int getNumEstrellas() {
        return numEstrellas;
    }

    public void setNumEstrellas(int numEstrellas) {
        this.numEstrellas = numEstrellas;
    }

    public ZonaPucp getZonaPucp() {
        return zonaPucp;
    }

    public void setZonaPucp(ZonaPucp zonaPucp) {
        this.zonaPucp = zonaPucp;
    }
}
