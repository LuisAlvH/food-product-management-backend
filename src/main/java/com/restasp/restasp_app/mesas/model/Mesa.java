package com.restasp.restasp_app.mesas.model;

public class Mesa {
    
    private Integer id_mesa;
    private String nombre_mesa;
    private String descripcion;
    private String estado;

    public Mesa(){}

    public Mesa(String nombre_mesa, String descripcion, String estado) {
        this.nombre_mesa = nombre_mesa;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Mesa(Integer id_mesa, String nombre_mesa, String descripcion, String estado) {
        this.id_mesa = id_mesa;
        this.nombre_mesa = nombre_mesa;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId_mesa() {
        return id_mesa;
    }

    public String getNombre_mesa() {
        return nombre_mesa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setId_mesa(Integer id_mesa) {
        this.id_mesa = id_mesa;
    }

    public void setNombre_mesa(String nombre_mesa) {
        this.nombre_mesa = nombre_mesa;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Mesa {" +
               "id_mesa=" + id_mesa +
               ", nombre_mesa='" + nombre_mesa + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", estado='" + estado + '\'' +
               '}';
    }
}

