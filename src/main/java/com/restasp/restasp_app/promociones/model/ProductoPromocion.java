package com.restasp.restasp_app.promociones.model;

public class ProductoPromocion {
 private Integer id_producto_promo;
 private String nombre_producto_promo;
 private String descrip_producto_promo;
 private Integer cantidad_producto_promo;

    public ProductoPromocion() {}

    public ProductoPromocion(Integer id_producto_promo, String nombre_producto_promo, String descrip_producto_promo, Integer cantidad_producto_promo) {
        this.id_producto_promo = id_producto_promo;
        this.nombre_producto_promo = nombre_producto_promo;
        this.descrip_producto_promo = descrip_producto_promo;
        this.cantidad_producto_promo = cantidad_producto_promo;
    }

    // Getters y Setters
    public Integer getId_producto_promo() {
        return id_producto_promo;
    }

    public void setId_producto_promo(Integer id_producto_promo) {
        this.id_producto_promo = id_producto_promo;
    }

    public String getNombre_producto_promo() {
        return nombre_producto_promo;
    }

    public void setNombre_producto_promo(String nombre_producto_promo) {
        this.nombre_producto_promo = nombre_producto_promo;
    }

    public String getDescrip_producto_promo() {
        return descrip_producto_promo;
    }

    public void setDescrip_producto_promo(String descrip_producto_promo) {
        this.descrip_producto_promo = descrip_producto_promo;
    }

    public Integer getCantidad_producto_promo() {
        return cantidad_producto_promo;
    }

    public void setCantidad_producto_promo(Integer cantidad_producto_promo) {
        this.cantidad_producto_promo = cantidad_producto_promo;
    }

    // toString
    @Override
    public String toString() {
        return "ProductoPromo{" +
                "id_producto_promo=" + id_producto_promo +
                ", nombre_producto_promo='" + nombre_producto_promo + '\'' +
                ", descrip_producto_promo='" + descrip_producto_promo + '\'' +
                ", cantidad_producto_promo=" + cantidad_producto_promo +
                '}';
    }


}
