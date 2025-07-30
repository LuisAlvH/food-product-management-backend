package com.restasp.restasp_app.promociones.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restasp.restasp_app.promociones.model.Promocion;
import com.restasp.restasp_app.promociones.repository.PromocionRepository;

@Service
public class PromocionServiceImpl implements PromocionService {

    private final PromocionRepository promocionRepository;
    @Autowired
    public PromocionServiceImpl(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    @Override
    public List<Promocion> listarPromociones() {
        return promocionRepository.getAll();
    }

    @Override
    public Optional<Promocion> obtenerPromocionPorId(int id) {
        return promocionRepository.getId(id);
    }

    @Override
    public void addPromocion(Promocion promocion) {
        promocionRepository.add(promocion);
    }

    @Override
    public void eliminarPromocion(int id) {
        promocionRepository.delete(id);
    }

    @Override
    public Promocion actualizarPromocion(int id, Promocion promocionActualizada) {
    promocionActualizada.setId_promocion(id);
    Promocion promoActualizada = promocionRepository.update(promocionActualizada);
     if (promoActualizada == null)throw new RuntimeException("No se encontró la promocion para actualizar con id " + id);      
        return promoActualizada;
    }
}
