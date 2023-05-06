package com.mps.MPSServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long idCargo;
//    @Column(columnDefinition = "varchar(16)")
    String nome;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE/*, CascadeType.PERSIST*/},
            mappedBy = "cargos")
    @JsonIgnore
    List<CredencialUsuario> credenciais = new ArrayList<>();

    public Cargo(String nome) {
        this.nome = nome;
    }
}
