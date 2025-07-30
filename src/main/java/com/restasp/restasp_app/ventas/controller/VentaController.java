package com.restasp.restasp_app.ventas.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restasp.restasp_app.ventas.model.Venta;
import com.restasp.restasp_app.ventas.service.VentaService;

@CrossOrigin(origins = "*") 
@RestController 
@RequestMapping("/ventas") 
public class VentaController {
    private final VentaService ventaService;
    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }
    @GetMapping
    public List<Venta> listar() {
        return ventaService.listarVentas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Integer id) {
        return ventaService.obtenerVentaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void crear(@RequestBody Venta venta) {
         ventaService.addVenta(venta);
    }

}
