package com.backend.core.models.dao;

import com.backend.core.models.entity.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura , Long> {

}
