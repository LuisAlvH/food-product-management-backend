package com.restasp.restasp_app.productos.repository;
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
import com.restasp.restasp_app.productos.model.Producto;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository{
    private Connection conn;


      public  ProductoRepositoryImpl (){
        conn=SqliteSingleton.getInstance().getConnection();
    }


    @Override
    public void add(Producto producto) {
        String sql = "INSERT INTO productos(nombre,descripcion,precio) VALUES (?, ?, ?)";
         try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }

    }

    @Override
    public void delete(int id) {
       String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
        
    }

    @Override
    public List<Producto> getAll() {
       List<Producto> productos = new ArrayList<>();
         
        String sql = "SELECT * FROM productos";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                productos.add(new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getString("estado"),
                    rs.getString("fecha_creacion")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }

        return productos;
    }

    @Override
    public Optional<Producto> getId(int id) {
       String sql = "SELECT * FROM productos WHERE id_producto = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getString("estado"),
                    rs.getString("fecha_creacion")
                );
                return Optional.of(producto); 
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
        }

        return Optional.empty();  
    }


    @Override
    public Producto update(Producto producto) {
           String sqlProductos = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, estado = ? WHERE id_producto = ? RETURNING id_producto, nombre, descripcion, precio, estado, fecha_creacion";
           try (PreparedStatement pstmt = conn.prepareStatement(sqlProductos)) {
            pstmt.setString(1, producto.getNombre());  
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setString(4, producto.getEstado()); 
            pstmt.setInt(5, producto.getId_producto());
          
            try(ResultSet rs =pstmt.executeQuery()){

                if (rs.next()) {
                return new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getString("estado"),
                    rs.getString("fecha_creacion")

                );
                } 
            }

        
        } catch (SQLException e) {
           
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        }
       return null;
       
    }


}
