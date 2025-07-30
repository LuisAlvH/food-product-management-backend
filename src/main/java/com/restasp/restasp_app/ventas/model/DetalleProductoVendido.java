package com.restasp.restasp_app.ventas.model;

public class DetalleProductoVendido {
    private Integer id_detalle_productoVendido;
    private Integer cantidad;
    private Integer id_producto;
    private Double precio;

    // Constructor vacío
    public DetalleProductoVendido() {
    }

    // Constructor con parámetros
    public DetalleProductoVendido(Integer id_detalle_productoVendido, Integer cantidad, Integer id_producto, Double precio) {
        this.id_detalle_productoVendido = id_detalle_productoVendido;
        this.cantidad = cantidad;
        this.id_producto = id_producto;
        this.precio = precio;
    }

    // Getters y Setters
    public Integer getId_detalle_productoVendido() {
        return id_detalle_productoVendido;
    }

    public void setId_detalle_productoVendido(Integer id_detalle_productoVendido) {
        this.id_detalle_productoVendido = id_detalle_productoVendido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    // toString()
    @Override
    public String toString() {
        return "DetalleProductoVendido{" +
                "id_detalle_productoVendido=" + id_detalle_productoVendido +
                ", cantidad=" + cantidad +
                ", id_producto=" + id_producto +
                ", precio=" + precio +
                '}';
    }
}
