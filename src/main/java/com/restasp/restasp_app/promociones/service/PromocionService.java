package com.restasp.restasp_app.promociones.service;
import java.util.List;
import java.util.Optional;
import com.restasp.restasp_app.promociones.model.Promocion;

public interface PromocionService {
        List<Promocion> listarPromociones();
        Optional<Promocion> obtenerPromocionPorId(int id); 
        void addPromocion(Promocion promocion);   
        void eliminarPromocion(int id);
        Promocion actualizarPromocion(int id,Promocion promocionActualizada);
}
