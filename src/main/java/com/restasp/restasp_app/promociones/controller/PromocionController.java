package com.restasp.restasp_app.promociones.controller;
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
import com.restasp.restasp_app.promociones.model.Promocion;
import com.restasp.restasp_app.promociones.service.PromocionService;

@CrossOrigin(origins = "*") 
@RestController 
@RequestMapping("/promociones") 
public class PromocionController {
    private final PromocionService promocionService;
    @Autowired
    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }
    @GetMapping
    public List<Promocion> listar() {
        return promocionService.listarPromociones();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Promocion> obtenerPorId(@PathVariable Integer id) {
        return promocionService.obtenerPromocionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public void crear(@RequestBody Promocion promocion) {
         promocionService.addPromocion(promocion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (promocionService.obtenerPromocionPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();}
        promocionService.eliminarPromocion(id);
        return ResponseEntity.noContent().build();
    }
     @PutMapping("/{id}")
    public ResponseEntity<Promocion> actualizar(@PathVariable Integer id, @RequestBody Promocion promocion) {
        if (promocionService.obtenerPromocionPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            Promocion promocionActualizada = promocionService.actualizarPromocion(id, promocion);
            return ResponseEntity.ok(promocionActualizada);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
