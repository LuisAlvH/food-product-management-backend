package com.restasp.restasp_app.mesas.repository;
import java.util.List;
import java.util.Optional;
import com.restasp.restasp_app.mesas.model.Mesa;


public interface MesaRepository {
    void add(Mesa mesa);
    Optional<Mesa> getId(int id);
    List<Mesa> getAll();
    void delete(int id) ;
    Mesa update( Mesa mesa);
}
