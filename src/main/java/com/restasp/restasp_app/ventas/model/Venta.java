package com.restasp.restasp_app.ventas.model;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class Venta {
    private Integer id_venta;
    private Double total;
    private String codigo_factura;
    private Integer id_usuario_venta;
    private String fecha_venta;
    @JsonIgnore
    private List<DetalleProductoVendido> productos_vendidos;
    @JsonIgnore
    private List<DetallePromocionVendida> promocion_vendidas;


    // Constructor vacío
    public Venta() {}

    // Constructor con parámetros
    public Venta(Integer id_venta, Double total, String codigo_factura, Integer id_usuario_venta,
                         String fecha_venta, List<DetalleProductoVendido> productos_vendidos,
                         List<DetallePromocionVendida> promocion_vendidas) {
        this.id_venta = id_venta;
        this.total = total;
        this.codigo_factura = codigo_factura;
        this.id_usuario_venta = id_usuario_venta;
        this.fecha_venta = fecha_venta;
        this.productos_vendidos = productos_vendidos;
        this.promocion_vendidas = promocion_vendidas;
    }
        public Venta(Integer id_venta, Double total, String codigo_factura, Integer id_usuario_venta,
                         String fecha_venta) {
        this.id_venta = id_venta;
        this.total = total;
        this.codigo_factura = codigo_factura;
        this.id_usuario_venta = id_usuario_venta;
        this.fecha_venta = fecha_venta;
        this.productos_vendidos = new ArrayList<>(); 
        this.promocion_vendidas = new ArrayList<>(); 
    }


    // Getters y Setters
    public Integer getId_venta() {
        return id_venta;
    }

    public void setId_venta(Integer id_venta) {
        this.id_venta = id_venta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCodigo_factura() {
        return codigo_factura;
    }

    public void setCodigo_factura(String codigo_factura) {
        this.codigo_factura = codigo_factura;
    }

    public Integer getId_usuario_venta() {
        return id_usuario_venta;
    }

    public void setId_usuario_venta(Integer id_usuario_venta) {
        this.id_usuario_venta = id_usuario_venta;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public List<DetalleProductoVendido> getProductos_vendidos() {
        return productos_vendidos;
    }

    public void setProductos_vendidos(List<DetalleProductoVendido> productos_vendidos) {
        this.productos_vendidos = productos_vendidos;
    }

    public List<DetallePromocionVendida> getPromocion_vendidas() {
        return promocion_vendidas;
    }

    public void setPromocion_vendidas(List<DetallePromocionVendida> promocion_vendidas) {
        this.promocion_vendidas = promocion_vendidas;
    }

    // toString()
    @Override
    public String toString() {
        return "VentaProducto{" +
                "id_venta=" + id_venta +
                ", total=" + total +
                ", codigo_factura='" + codigo_factura + '\'' +
                ", id_usuario_venta=" + id_usuario_venta +
                ", fecha_venta='" + fecha_venta + '\'' +
                ", productos_vendidos=" + productos_vendidos +
                ", promocion_vendidas=" + promocion_vendidas +
                '}';
    }
}
