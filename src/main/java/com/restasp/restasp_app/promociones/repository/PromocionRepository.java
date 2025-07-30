package com.restasp.restasp_app.promociones.repository;
import java.util.List;
import java.util.Optional;
import com.restasp.restasp_app.promociones.model.Promocion;



public interface PromocionRepository {
     void add(Promocion promocion); 
     Optional<Promocion> getId(int id);
     List<Promocion> getAll();
     void delete(int id) ;
     Promocion update(Promocion promocionActualizada);
}