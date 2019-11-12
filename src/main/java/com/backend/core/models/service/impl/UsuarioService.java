package com.backend.core.models.service.impl;

import com.backend.core.models.dao.IUserDao;
import com.backend.core.models.entity.Usuario;
import com.backend.core.models.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {


    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    private IUserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = userDao.findByUsername(username);
        if (usuario == null) {
            String error = "Error en el login: no existe el usuario '" + username + "' en el sistema";
            logger.error(error);
            throw new UsernameNotFoundException(error);
        }
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
