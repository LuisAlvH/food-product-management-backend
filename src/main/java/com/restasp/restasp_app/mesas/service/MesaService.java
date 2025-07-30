package com.restasp.restasp_app.mesas.service;
import java.util.List;
import java.util.Optional;
import com.restasp.restasp_app.mesas.model.Mesa;


public interface MesaService {

        List<Mesa> listarMesas();
        Optional<Mesa> obtenerMesaPorId(int id); 
        void addMesa(Mesa mesa);   
        void eliminarMesa(int id);
        Mesa actualizarMesa(int id,Mesa mesa);

}



