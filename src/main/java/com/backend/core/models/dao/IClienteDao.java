package com.backend.core.models.dao;

import com.backend.core.models.entity.Cliente;
import com.backend.core.models.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

    @Query("from Region")
    List<Region> findAllRegiones();
}
