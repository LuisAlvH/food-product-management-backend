package com.restasp.restasp_app.usuarios.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Usuario {

    private Integer id_usuario;
    private String nombre;
    private String rol;
    private String user;
    private String fecha_creacion;
    @JsonIgnore
    private String password;

    public Usuario(Integer id_usuario,String nombre,String rol,String user ,String fecha_creacion){
        this.id_usuario=id_usuario;
        this.nombre=nombre;
        this.rol=rol;
        this.user=user;
        this.fecha_creacion=fecha_creacion;
    }
    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
   public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                ", user='" + user + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                '}';
    }
    
}
