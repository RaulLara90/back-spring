package com.backend.core.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "No puede estar vacío")
    @Size(min = 4, max = 12, message = "El tamaño tiene que estar entre 4 y 12 caracteres")
    private String nombre;

    @NotEmpty(message = "No puede estar vacío")
    private String apellido;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "No puede estar vacío")
    @Email(message = "No es una dirección de email bien formada")
    private String email;

    @NotNull(message = "No puede estar vacio")
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    private String foto;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"cliente", "hibernateLazyInitializer", "handler"}, allowGetters = true)
    private List<Factura> facturas;

    public Cliente() {
        this.facturas = new ArrayList<>();
    }
}
