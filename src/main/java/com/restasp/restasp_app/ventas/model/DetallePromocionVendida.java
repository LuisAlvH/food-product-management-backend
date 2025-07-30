package com.restasp.restasp_app.ventas.model;

public class DetallePromocionVendida {

    private Integer id_detalle_promocionVendida;
    private Double precio_unitario;
    private Integer cantidad;
    private Integer id_promocion;

    // Constructor vacío
    public DetallePromocionVendida() {
    }

    // Constructor con parámetros
    public DetallePromocionVendida(Integer id_detalle_promocionVendida, Double precio_unitario, Integer cantidad, Integer id_promocion) {
        this.id_detalle_promocionVendida = id_detalle_promocionVendida;
        this.precio_unitario = precio_unitario;
        this.cantidad = cantidad;
        this.id_promocion = id_promocion;
    }

    // Getters y Setters
    public Integer getId_detalle_promocionVendida() {
        return id_detalle_promocionVendida;
    }

    public void setId_detalle_promocionVendida(Integer id_detalle_promocionVendida) {
        this.id_detalle_promocionVendida = id_detalle_promocionVendida;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_promocion() {
        return id_promocion;
    }

    public void setId_promocion(Integer id_promocion) {
        this.id_promocion = id_promocion;
    }

    // toString()
    @Override
    public String toString() {
        return "DetallePormocionVendida{" +
                "id_detalle_promocionVendida=" + id_detalle_promocionVendida +
                ", precio_unitario=" + precio_unitario +
                ", cantidad=" + cantidad +
                ", id_promocion=" + id_promocion +
                '}';
    }
}
