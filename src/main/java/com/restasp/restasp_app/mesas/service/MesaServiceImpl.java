package com.restasp.restasp_app.mesas.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restasp.restasp_app.mesas.model.Mesa;
import com.restasp.restasp_app.mesas.repository.MesaRepository;


@Service
public class MesaServiceImpl implements MesaService  {


    private final MesaRepository mesaRepository;
    
    @Autowired
    public MesaServiceImpl(MesaRepository mesaRepository) {
            this.mesaRepository = mesaRepository;
        
    }

    @Override
    public void addMesa(Mesa mesa) {
        mesaRepository.add(mesa);
        
    }

    @Override
    public void eliminarMesa(int id) {
        mesaRepository.delete(id);
        
    }

    @Override
    public List<Mesa> listarMesas() {
        return mesaRepository.getAll();
    }

    @Override
    public Optional<Mesa> obtenerMesaPorId(int id) {
         return mesaRepository.getId(id);
    }

    @Override
    public Mesa actualizarMesa(int id, Mesa mesa) {
    mesa.setId_mesa(id);
    Mesa mesaActualizada = mesaRepository.update(mesa);
     if (mesaActualizada == null)throw new RuntimeException("No se encontró la mesa para actualizar con id " + id);      
        return mesaActualizada;
    }




}
