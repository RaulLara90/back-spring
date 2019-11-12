package com.backend.core.models.dao;

import com.backend.core.models.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoDao extends CrudRepository<Producto, Long> {

    @Query("select p from Producto p where p.nombre like %?1%")
    List<Producto> findByNombre(String term);
    List<Producto> findByNombreContainingIgnoreCase(String term);
    List<Producto> findByNombreStartingWithIgnoreCase(String term);
}
