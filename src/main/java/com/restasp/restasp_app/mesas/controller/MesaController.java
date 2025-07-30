package com.restasp.restasp_app.mesas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restasp.restasp_app.mesas.model.Mesa;
import com.restasp.restasp_app.mesas.service.MesaService;



@CrossOrigin(origins = "*") 
@RestController 
@RequestMapping("/mesas") 
public class MesaController {

private final MesaService mesaService;

    
    @Autowired
    public  MesaController (MesaService mesaService) {
       this.mesaService = mesaService;
    }

    @GetMapping
    public List<Mesa> listar() {
        return mesaService.listarMesas();
    
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> obtenerPorId(@PathVariable Integer id) {
        return mesaService.obtenerMesaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
     @PostMapping
    public void crear(@RequestBody Mesa mesa) {
         mesaService.addMesa(mesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (mesaService.obtenerMesaPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mesaService.eliminarMesa(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Mesa> actualizar(@PathVariable Integer id, @RequestBody Mesa mesa) {
        if (mesaService.obtenerMesaPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            Mesa mesaActualizada = mesaService.actualizarMesa(id, mesa);
            return ResponseEntity.ok(mesaActualizada);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
