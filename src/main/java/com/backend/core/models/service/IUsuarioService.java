package com.backend.core.models.service;

import com.backend.core.models.entity.Usuario;

public interface IUsuarioService {

    Usuario findByUsername(String username);
}
