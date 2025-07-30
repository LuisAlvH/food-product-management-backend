package com.restasp.restasp_app.config;

import org.springframework.stereotype.Component;

import com.restasp.restasp_app.BdRestaSp.SqliteSingleton;

import jakarta.annotation.PreDestroy;

@Component
public class AppShutdownHook {

    @PreDestroy
    public void onShutdown() {
        SqliteSingleton.getInstance().closeConnection();
        System.out.println("Conexión SQLite cerrada al finalizar la aplicación");
    }
}