package com.restasp.restasp_app.ventas.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.restasp.restasp_app.BdRestaSp.SqliteSingleton;
import com.restasp.restasp_app.ventas.model.DetalleProductoVendido;
import com.restasp.restasp_app.ventas.model.DetallePromocionVendida;
import com.restasp.restasp_app.ventas.model.Venta;



@Repository
public class VentaRepositoryImpl  implements VentaRepository{
     private Connection conn;

    public  VentaRepositoryImpl (){
        conn=SqliteSingleton.getInstance().getConnection();
    }
    @Override
    public void add(Venta venta) {
        int idGenerado = -1;
        String sqlVenta  = "INSERT INTO ventas(total,codigo_factura,fk_usuario) VALUES (?, ?, ?)";

          try (PreparedStatement pstmt = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, venta.getTotal());
            pstmt.setString(2, venta.getCodigo_factura());
            pstmt.setInt(3, venta.getId_usuario_venta());

            int filasAfectadas = pstmt.executeUpdate();
            if(filasAfectadas > 0){
                /*USAMOS TRY WITH RESOURSE */
                try (ResultSet rs =pstmt.getGeneratedKeys()){
                   if(rs.next()){
                     idGenerado = rs.getInt(1);
                     venta.setId_venta(idGenerado);
                   }     
                } 
            }

            if(venta.getProductos_vendidos()!=null && !venta.getProductos_vendidos().isEmpty()){

                String sqlDetalleProducto = "INSERT INTO detalles_ventas_productos(precio_unitario, cantidad, fk_venta, fk_producto) VALUES (?, ?, ?, ?)";

                try (PreparedStatement pstmt2 = conn.prepareStatement(sqlDetalleProducto)) {
                    for (DetalleProductoVendido detalleProducto : venta.getProductos_vendidos()) {
                        pstmt2.setDouble(1, detalleProducto.getPrecio());
                        pstmt2.setInt(2, detalleProducto.getCantidad());
                        pstmt2.setInt(3, venta.getId_venta());
                        pstmt2.setInt(4, detalleProducto.getId_producto());
                        pstmt2.addBatch(); 
                    }
                pstmt2.executeBatch(); 
                }
            }

            if(venta.getPromocion_vendidas()!=null && !venta.getPromocion_vendidas().isEmpty()){

                String sqlDetallePromocion = "INSERT INTO detalles_ventas_promociones(precio_unitario, cantidad, fk_venta, fk_promocion) VALUES (?, ?, ?, ?)";

                try (PreparedStatement pstmt2 = conn.prepareStatement(sqlDetallePromocion)) {
                    for (DetallePromocionVendida detallePromocion : venta.getPromocion_vendidas()) {
                        pstmt2.setDouble(1, detallePromocion.getPrecio_unitario());
                        pstmt2.setInt(2, detallePromocion.getCantidad());
                        pstmt2.setInt(3, venta.getId_venta());
                        pstmt2.setInt(4, detallePromocion.getId_promocion());
                        pstmt2.addBatch(); 
                    }
                pstmt2.executeBatch(); 
                }
            }
            

        } catch (SQLException e) {
            System.err.println("Error al insertar la venta: " + e.getMessage());
        }
    }

    @Override
    public List<Venta> getAll() {
        List<Venta> ventas = new ArrayList<>();
        String sqlVenta = "SELECT * FROM ventas";
         try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlVenta)) {

            while (rs.next()) {
                ventas.add(new Venta(
                    rs.getInt("id_venta"),
                    rs.getDouble("total"),
                    rs.getString("codigo_factura"),
                    rs.getInt("fk_usuario"),
                    rs.getString("fecha_venta")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar ventas: " + e.getMessage());
        }

     return ventas;
    }


    @Override
    public Optional<Venta> getId(int id) {

        String sql = "SELECT * FROM ventas WHERE id_venta = ?";

          try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Venta venta = new Venta(
                    rs.getInt("id_venta"),
                    rs.getDouble("total"),
                    rs.getString("codigo_factura"),
                    rs.getInt("fk_usuario"),
                    rs.getString("fecha_venta")
                );
                return Optional.of(venta); 
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la venta: " + e.getMessage());
        }

        return Optional.empty();  


    }

  

}
