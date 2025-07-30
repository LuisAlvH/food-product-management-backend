package com.restasp.restasp_app.BdRestaSp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablasSqlite {
    

    public static void crearTablas(Connection conecc){
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
        + "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nombre TEXT NOT NULL, "
        + "rol TEXT NOT NULL, "
        + "username TEXT NOT NULL, "
        + "fecha_creacion TEXT NOT NULL DEFAULT (datetime('now', 'localtime')),"
        + "password TEXT NOT NULL"
        + ");";
              
         String sqlVentas = "CREATE TABLE IF NOT EXISTS ventas ("
        + "id_venta INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "total REAL NOT NULL, "
        + "codigo_factura TEXT NOT NULL, "
        + "fecha_venta TEXT NOT NULL DEFAULT (datetime('now', 'localtime')),"
        + "fk_usuario INTEGER NOT NULL,"
        + "FOREIGN KEY(fk_usuario) REFERENCES usuarios(id_usuario)"
        + ");";

          String sqlProductos = "CREATE TABLE IF NOT EXISTS productos ("
        + "id_producto INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nombre TEXT NOT NULL, "
        + "descripcion TEXT NOT NULL, "
        + "precio REAL NOT NULL, "
        + "estado TEXT NOT NULL DEFAULT 'PENDIENTE' CHECK(estado IN ('ACTIVO','BLOQUEADO','PENDIENTE')), "
        + "fecha_creacion TEXT NOT NULL DEFAULT (datetime('now', 'localtime'))"
        + ");"; 

            String sqlPromociones = "CREATE TABLE IF NOT EXISTS promociones (" 
        + "id_promocion INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "total_promo REAL NOT NULL, "
        + "nombre TEXT NOT NULL, "
        + "descripcion TEXT NOT NULL, "
        + "fecha_creacion TEXT NOT NULL DEFAULT (datetime('now', 'localtime'))"
        + ");"; 

               String sqlProductosPromo = "CREATE TABLE IF NOT EXISTS produc_promo (" 
        + "id_produc_promo INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "cantidad INTEGER NOT NULL, "
        + "fk_promocion INTEGER NOT NULL, "
        + "fk_producto INTEGER NOT NULL, "
        + "FOREIGN KEY(fk_producto) REFERENCES productos(id_producto), "
        + "FOREIGN KEY(fk_promocion) REFERENCES promociones(id_promocion) "
        + ");"; 
        
            String sqlDetallesVentasProductos = "CREATE TABLE IF NOT EXISTS detalles_ventas_productos ("
        + "id_detalle_venta_pro  INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "precio_unitario REAL NOT NULL, "
        + "cantidad INTEGER NOT NULL, "
        + "subtotal REAL GENERATED ALWAYS AS (cantidad * precio_unitario) STORED, "
        + "fk_venta INTEGER NOT NULL, "
        + "fk_producto INTEGER NOT NULL, "
        + "FOREIGN KEY(fk_venta) REFERENCES ventas(id_venta), "
        + "FOREIGN KEY(fk_producto) REFERENCES productos(id_producto) "
        + ");"; 
        
            String sqlDetallesVentasProductosPromo = "CREATE TABLE IF NOT EXISTS detalles_ventas_promociones ("
        + "id_detalle_venta_prom INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "precio_unitario REAL NOT NULL, "
        + "cantidad INTEGER NOT NULL, "
        + "subtotal REAL GENERATED ALWAYS AS (cantidad * precio_unitario) STORED, "
        + "fk_venta INTEGER NOT NULL, "
        + "fk_promocion INTEGER NOT NULL, "
        + "FOREIGN KEY(fk_venta) REFERENCES ventas(id_venta), "
        + "FOREIGN KEY(fk_promocion) REFERENCES promociones(id_promocion) "
        + ");"; 

           String sqlMesas = "CREATE TABLE IF NOT EXISTS mesas (" 
        + "id_mesa INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "nombre_mesa TEXT NOT NULL, "
        + "descripcion TEXT NOT NULL, "
        + "estado TEXT NOT NULL DEFAULT 'DISPONIBLE' "
        + "CHECK(estado IN ('DISPONIBLE','OCUPADA','RESERVADA','FUERA_DE_SERVICIO'))"
        + ");"; 

        try (Statement stmt = conecc.createStatement()) {
            stmt.execute(sqlUsuarios);
            stmt.execute(sqlVentas);
            stmt.execute(sqlProductos);
            stmt.execute(sqlPromociones);
            stmt.execute(sqlProductosPromo);
            stmt.execute(sqlDetallesVentasProductos);
            stmt.execute(sqlDetallesVentasProductosPromo);
            stmt.execute(sqlMesas);

         String checkAdmin = "SELECT COUNT(*) AS count FROM usuarios WHERE rol = 'admin'";

                try (ResultSet rs = stmt.executeQuery(checkAdmin)) {

                    if (rs.next() && rs.getInt("count") == 0) {
                        
                        String insertAdmin = "INSERT INTO usuarios (nombre, rol, username, password) VALUES "
                                + "('Administrador', 'admin', 'admin', 'admin1234')";
                        stmt.executeUpdate(insertAdmin);
                        
                    }
                }

         String checkMesas = "SELECT COUNT(*) AS count FROM mesas";

                try (ResultSet rs = stmt.executeQuery(checkMesas)) {
                    if (rs.next() && rs.getInt("count") == 0) {
                            String insertMesas = "INSERT INTO mesas (nombre_mesa, descripcion, estado) VALUES "
                                + "('Mesa 1', 'Mesa cerca de la ventana', 'DISPONIBLE'),"
                                + "('Mesa 2', 'Mesa junto a la barra', 'OCUPADA'),"
                                + "('Mesa 3', 'Mesa en la terraza', 'RESERVADA'),"
                                + "('Mesa 4', 'Mesa VIP con vista panorámica', 'FUERA_DE_SERVICIO');";
                            stmt.executeUpdate(insertMesas);
                        }
                }


    
       String insertProducto = "INSERT INTO productos (nombre, descripcion, precio, estado) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conecc.prepareStatement(insertProducto)) {
                    for (int i = 1; i <= 10; i++) {
                        ps.setString(1, "Producto " + i);
                        ps.setString(2, "Descripción del producto " + i);
                        ps.setDouble(3, 10.0 + i);
                        ps.setString(4, "ACTIVO");
                        ps.executeUpdate();
                    }
                }


        } catch (SQLException  e) {
            System.err.println("Error creando tablas: " + e.getMessage());
        }

    }

}
