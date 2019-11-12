package com.backend.core.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String observacion;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"facturas", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    private Cliente cliente;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ItemFactura> items;

    public Factura() {
        items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Double getTotal() {
        Double total = 0.00;
        for (ItemFactura item : items) {
            total += item.getImporte();
        }
        return total;
    }

}
