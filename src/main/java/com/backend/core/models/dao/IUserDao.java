package com.backend.core.models.dao;

import com.backend.core.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    @Query("select u from Usuario u where u.username=?1     ")
    Usuario findByUsername2(String username);
}
