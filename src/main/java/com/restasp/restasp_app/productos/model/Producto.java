package com.restasp.restasp_app.productos.model;



public class Producto {

    private Integer id_producto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String estado;
    private String fecha_creacion;

    public Producto() {}
     public Producto(Integer id_producto, String nombre, String descripcion, Double precio, String estado,String fecha_creacion) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.fecha_creacion=fecha_creacion;
    }
    public Producto(Integer id_producto, String nombre, String descripcion, Double precio, String estado) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }
    /*PRODUCTO QUE ESTA EN UNA PROMO */
    public Producto(String nombre , String descripcion){
          this.nombre = nombre;
          this.descripcion = descripcion;
    }
 
    // Getters y Setters
    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
     public String getFecha_creacion() {
        return fecha_creacion;
    }
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
@Override
public String toString() {
    return "Producto{" +
            "id_producto=" + id_producto +
            ", nombre='" + nombre + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", precio=" + precio +
            ", fecha apertura="+fecha_creacion +'\''+
            ", estado='" + estado + '\'' +
            '}';
}

}
