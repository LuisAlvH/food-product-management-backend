package com.restasp.restasp_app.ventas.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restasp.restasp_app.ventas.model.Venta;
import com.restasp.restasp_app.ventas.repository.VentaRepository;


@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    @Autowired
    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.getAll();
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(int id) {
       return ventaRepository.getId(id);
    }

    @Override
    public void addVenta(Venta venta) {
        ventaRepository.add(venta);
    }

}
