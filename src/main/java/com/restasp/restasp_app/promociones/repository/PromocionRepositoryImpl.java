package com.restasp.restasp_app.promociones.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.restasp.restasp_app.BdRestaSp.SqliteSingleton;
import com.restasp.restasp_app.promociones.model.ProductoPromocion;
import com.restasp.restasp_app.promociones.model.Promocion;



@Repository
public class PromocionRepositoryImpl implements PromocionRepository {


    private Connection conn;

    public  PromocionRepositoryImpl (){
        conn=SqliteSingleton.getInstance().getConnection();
    }

    @Override
    public void add(Promocion promocion) {

        int idGenerado = -1;
        String sqlPromocion  = "INSERT INTO promociones(total_promo,nombre,descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlPromocion, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, promocion.getPrecio_promo());
            pstmt.setString(2, promocion.getNombre_promo());
            pstmt.setString(3, promocion.getDescrip_promo());
            int filasAfectadas = pstmt.executeUpdate();
            if(filasAfectadas > 0){
                /*USAMOS TRY WITH RESOURSE */
                try (ResultSet rs =pstmt.getGeneratedKeys()){
                   if(rs.next()){
                     idGenerado = rs.getInt(1);
                     promocion.setId_promocion(idGenerado);
                   }     
                } 
            }
            if(promocion.getProductos_promo()!=null && !promocion.getProductos_promo().isEmpty()){
                String sqlProductoPromo = "INSERT INTO produc_promo(cantidad,fk_promocion,fk_producto) VALUES (?, ?, ?)";     
                try (PreparedStatement pstmt2 = conn.prepareStatement(sqlProductoPromo)) {
                    for (ProductoPromocion productoPromo : promocion.getProductos_promo()) {
                        pstmt2.setInt(1, productoPromo.getCantidad_producto_promo());
                        pstmt2.setInt(2, promocion.getId_promocion());
                        pstmt2.setInt(3, productoPromo.getId_producto_promo());
                        pstmt2.addBatch(); 
                    }
                pstmt2.executeBatch(); 
                }
            }    
        } catch (SQLException e) {
            System.err.println("Error al insertar la promocion: " + e.getMessage());
        }
    }

    
    
    @Override
    public List<Promocion> getAll() {

        List<Promocion> promociones = new ArrayList<>();


        String sqlPromociones = 
            "SELECT " +
            "p.total_promo AS precio_promo, " +
            "p.nombre AS nombre_promo, " +
            "p.descripcion AS descrip_promo, " +
            "pp.cantidad AS cantidad_producto, " +
            "ps.nombre AS nombre_producto_promo, " +
            "p.id_promocion AS id_promocion, " +
            "ps.descripcion AS descripcion_producto,"+
            "pp.id_produc_promo AS id_producto_promo "+
            "FROM promociones p " +
            "JOIN produc_promo pp ON p.id_promocion = pp.fk_promocion " +
            "JOIN productos ps ON pp.fk_producto = ps.id_producto;";


        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlPromociones)) {
            
            Map<Integer, Promocion> promoMap = new HashMap<>();

            while (rs.next()) {

                int idPromo=rs.getInt("id_promocion");
                Promocion promo = promoMap.get(idPromo);

                if (promo == null) {
                    promo = new Promocion(
                        idPromo,
                        rs.getDouble("precio_promo"),
                        rs.getString("nombre_promo"),
                        rs.getString("descrip_promo"),
                        null
                    );
                   promoMap.put(idPromo, promo);
                }
                ProductoPromocion prodPromo = new ProductoPromocion(
                    rs.getInt("id_producto_promo"),
                    rs.getString("nombre_producto_promo"),
                    rs.getString("descripcion_producto"),
                    rs.getInt("cantidad_producto")
                 );

                promo.getProductos_promo().add(prodPromo);
            }

            promociones.addAll(promoMap.values());

        } catch (SQLException e) {
            System.err.println("Error al listar promociones:" + e.getMessage());
        }

         return promociones;
    }

    
    
    
    
    @Override
    public Optional<Promocion> getId(int id) {

        String sqlPromociones = 
                "SELECT " +
                "p.id_promocion AS id_promocion, " +
                "p.total_promo AS precio_promo, " +
                "p.nombre AS nombre_promo, " +
                "p.descripcion AS descrip_promo, " +
                "pp.cantidad AS cantidad_producto, " +
                "ps.nombre AS nombre_producto_promo, " +
                "ps.descripcion AS descripcion_producto, " +
                "pp.id_produc_promo AS id_producto_promo " +
                "FROM promociones p " +
                "JOIN produc_promo pp ON p.id_promocion = pp.fk_promocion " +
                "JOIN productos ps ON pp.fk_producto = ps.id_producto " +
                "WHERE p.id_promocion = ?;";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlPromociones)) {
            pstmt.setInt(1, id);
        
            try(ResultSet rs = pstmt.executeQuery()){;
                Promocion promo=null;
                while (rs.next()) {
                    if (promo == null) {
                        promo = new Promocion(
                            rs.getInt("id_promocion"),
                            rs.getDouble("precio_promo"),
                            rs.getString("nombre_promo"),
                            rs.getString("descrip_promo"),
                            null
                        );
                    }
                 ProductoPromocion prodPromo = new ProductoPromocion(
                        rs.getInt("id_producto_promo"),
                        rs.getString("nombre_producto_promo"),
                        rs.getString("descripcion_producto"),
                        rs.getInt("cantidad_producto")
                 );
                   promo.getProductos_promo().add(prodPromo);    
                }

            return promo != null ? Optional.of(promo) : Optional.empty();
        }

        } catch (SQLException e) {
            System.err.println("Error al obtener la promocion: " + e.getMessage());
            return Optional.empty();  
        }
    }


    @Override
    public void delete(int id) {
        Optional<Promocion> promocionOpt= getId(id);
        if(promocionOpt.isEmpty()){
            System.out.println("No se encontró la promoción con ID: " + id);
            return;
        }
        Promocion promo = promocionOpt.get();
        String sqlProductoPromo = "DELETE FROM produc_promo WHERE id_produc_promo = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlProductoPromo)) {
                for (ProductoPromocion productoPromo : promo.getProductos_promo()) {
                    pstmt.setInt(1, productoPromo.getId_producto_promo());
                    pstmt.addBatch(); 
                }
                pstmt.executeBatch();

            }catch (SQLException e) {
            System.err.println("Error al eliminar los productos de la promoción: " + e.getMessage());
            }

        String sqlPromocion = "DELETE FROM promociones WHERE id_promocion = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlPromocion)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("Promoción eliminada correctamente (ID: " + id + ").");
            } catch (SQLException e) {
                System.err.println("Error al eliminar la promoción con ID " + id + ": " + e.getMessage());
            }   
     
    }

   
    @Override
    public Promocion update(Promocion promocionActualizada) {
    Optional<Promocion> promocionExistenteOpt = getId(promocionActualizada.getId_promocion());
        if (promocionExistenteOpt.isEmpty()) {
            System.out.println("No se encontró la promoción con ID: " + promocionActualizada.getId_promocion());
            return null;
        }

    Promocion promocionExistente = promocionExistenteOpt.get();

    try {
        // 1. Eliminamos todos los producto de la promocion
        String sqlDelete = "DELETE FROM produc_promo WHERE fk_promocion = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {
            pstmt.setInt(1, promocionExistente.getId_promocion());
            pstmt.executeUpdate();
        }

        // 2. Insertar nuevos productos (si hay)
        if (promocionActualizada.getProductos_promo() != null &&
            !promocionActualizada.getProductos_promo().isEmpty()) {
            
            String sqlInsert = "INSERT INTO produc_promo(cantidad, fk_promocion, fk_producto) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                for (ProductoPromocion productoPromo : promocionActualizada.getProductos_promo()) {
                    pstmt.setInt(1, productoPromo.getCantidad_producto_promo());
                    pstmt.setInt(2, promocionActualizada.getId_promocion());
                    pstmt.setInt(3, productoPromo.getId_producto_promo());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
        }

        // 3. Actualizar promoción
        String sqlUpdate =
            "UPDATE promociones SET total_promo = ?, nombre = ?, descripcion = ? " +
            "WHERE id_promocion = ? " +
            "RETURNING id_promocion, total_promo, nombre, descripcion";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setDouble(1, promocionActualizada.getPrecio_promo());
            pstmt.setString(2, promocionActualizada.getNombre_promo());
            pstmt.setString(3, promocionActualizada.getDescrip_promo());
            pstmt.setInt(4, promocionActualizada.getId_promocion());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Promocion(
                        rs.getInt("id_promocion"),
                        rs.getDouble("total_promo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        promocionActualizada.getProductos_promo()
                    );
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al actualizar la promoción: " + e.getMessage());
    }

    return null;
}


}
