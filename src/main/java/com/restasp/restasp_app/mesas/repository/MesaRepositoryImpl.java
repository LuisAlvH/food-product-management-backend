package com.restasp.restasp_app.mesas.repository;
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
import com.restasp.restasp_app.mesas.model.Mesa;



@Repository
public class MesaRepositoryImpl  implements MesaRepository{

      private Connection conn;


      public  MesaRepositoryImpl (){
        conn=SqliteSingleton.getInstance().getConnection();
    }


      @Override
      public void add(Mesa mesa) {
         String sql = "INSERT INTO mesas(nombre_mesa,descripcion) VALUES (?, ?)";

         try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mesa.getNombre_mesa());
            pstmt.setString(2, mesa.getDescripcion());
       
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar una mesa: " + e.getMessage());
        }
        
      }


      @Override
      public void delete(int id) {
         String sql = "DELETE FROM mesas WHERE id_mesa = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar una mesa: " + e.getMessage());
        }
        
      }


      @Override
      public List<Mesa> getAll() {

        List<Mesa> mesas = new ArrayList<>();
         
        String sql = "SELECT * FROM mesas";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                mesas.add(new Mesa(
                    rs.getInt("id_mesa"),
                    rs.getString("nombre_mesa"),
                    rs.getString("descripcion"),
                    rs.getString("estado")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar las mesas: " + e.getMessage());
        }

        return mesas;
      }


      @Override
      public Optional<Mesa> getId(int id) {
        String sql = "SELECT * FROM mesas WHERE id_mesa = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Mesa mesa = new Mesa(
                    rs.getInt("id_mesa"),
                    rs.getString("nombre_mesa"),
                    rs.getString("descripcion"),
                    rs.getString("estado")
                  
                );
                return Optional.of(mesa); 
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la mesa: " + e.getMessage());
        }

        return Optional.empty();  
      }


      @Override
      public Mesa update(Mesa mesa)  {  
        
        String sqlMesas = "UPDATE mesas SET nombre_mesa = ?, descripcion = ?, estado = ? WHERE id_mesa= ? RETURNING id_mesa, nombre_mesa, descripcion, estado";
        /*Apartir de 3.35 sqlite presentamos returning , DEVUELVE FILA ACTUALIZADA*/


        
        try (PreparedStatement pstmt = conn.prepareStatement(sqlMesas)) {
            pstmt.setString(1, mesa.getNombre_mesa());  
            pstmt.setString(2, mesa.getDescripcion());
            pstmt.setString(3, mesa.getEstado());
            pstmt.setInt(4, mesa.getId_mesa()); 
          
          

            try(ResultSet rs =pstmt.executeQuery()){

                if (rs.next()) {
            
                return new Mesa(
                    rs.getInt("id_mesa"),
                    rs.getString("nombre_mesa"),
                    rs.getString("descripcion"),
                    rs.getString("estado")
                );
                } 
            }


        
        } catch (SQLException e) {
            System.err.println("Error al actualizar la mesa: " + e.getMessage());
        }
       return null;
      }

}
