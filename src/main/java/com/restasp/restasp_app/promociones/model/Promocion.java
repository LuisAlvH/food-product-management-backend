package com.restasp.restasp_app.promociones.model;
import java.util.ArrayList;
import java.util.List;
public class Promocion {
private Integer id_promocion;
private Double precio_promo;
private String nombre_promo;
private String descrip_promo;
private List<ProductoPromocion> productos_promo;
    public Promocion() {
        this.productos_promo = new ArrayList<>();
    }
    // Constructor con todos los atributos
    public Promocion(Integer id_promocion, Double precio_promo, String nombre_promo, String descrip_promo, List<ProductoPromocion> productos_promo) {
        this.id_promocion = id_promocion;
        this.precio_promo = precio_promo;
        this.nombre_promo = nombre_promo;
        this.descrip_promo = descrip_promo;
        this.productos_promo = (productos_promo != null) ? productos_promo : new ArrayList<>();
    }
// Getters y Setters
    public Integer getId_promocion() {
        return id_promocion;
    }
    public void setId_promocion(Integer id_promocion) {
        this.id_promocion = id_promocion;
    }
    public Double getPrecio_promo() {
        return precio_promo;
    }
    public void setPrecio_promo(Double precio_promo) {
        this.precio_promo = precio_promo;
    }
    public String getNombre_promo() {
        return nombre_promo;
    }
    public void setNombre_promo(String nombre_promo) {
        this.nombre_promo = nombre_promo;
    }
    public String getDescrip_promo() {
        return descrip_promo;
    }
    public void setDescrip_promo(String descrip_promo) {
        this.descrip_promo = descrip_promo;
    }
    public List<ProductoPromocion> getProductos_promo() {
        return productos_promo;
    }
    public void setProductos_promo(List<ProductoPromocion> productos_promo) {
        this.productos_promo = productos_promo;
    }
    // toString
    @Override
    public String toString() {
        return "Promocion{" +
                "id_promocion=" + id_promocion +
                ", precio_promo=" + precio_promo +
                ", nombre_promo='" + nombre_promo + '\'' +
                ", descrip_promo='" + descrip_promo + '\'' +
                ", productos_promo=" + productos_promo +
                '}';
    }
}
