package com.restasp.restasp_app.BdRestaSp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqliteSingleton  {

    private static SqliteSingleton instance;
    private Connection connection;
    private static final String URL = "jdbc:sqlite:restasp.db";

    private SqliteSingleton(){

        try{
            connection=DriverManager.getConnection(URL);
            TablasSqlite.crearTablas(connection);
        }catch(SQLException  e){
             throw new RuntimeException("Error al conectar con SQLite", e);

        }

    }

    public static synchronized SqliteSingleton getInstance(){
        if(instance==null) instance=new SqliteSingleton();
          
         return instance;
    }

     public Connection getConnection() {
        return connection;
    }

    
    public void closeConnection(){
        try {
            if(connection!=null && !connection.isClosed())connection.close();
         
        } catch (SQLException  e) {
             System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }

    }
    
}
