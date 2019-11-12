package com.backend.core.models.service;

import com.backend.core.models.entity.Cliente;
import com.backend.core.models.entity.Factura;
import com.backend.core.models.entity.Producto;
import com.backend.core.models.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    List<Cliente> findAll();

    Page<Cliente> findAll(Pageable pageable);

    Cliente findById(Long id);

    Cliente save(Cliente cliente);

    void delete(Long id);

    List<Region> findAllRegiones();

    Factura findFacturaById(Long id);

    Factura saveFactura(Factura factura);

    void deleteFacturaById(Long id);

    List<Producto> findProductoByNombre(String term);
}
