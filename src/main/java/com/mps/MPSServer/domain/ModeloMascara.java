package com.mps.MPSServer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModeloMascara {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private short idModeloMascara;

    @Column(columnDefinition = "varchar(20)")
    private String modelo;

    @Column(columnDefinition = "enum('Profissional', 'Domestico')")
    private String uso;

    @Column(columnDefinition = "set('Hipoclorito-1', 'Hipoclorito-0_01', 'UV_Baixa', 'Agua_Sabao')")
    private String recondicionamento;

    private short ciclos;

    public ModeloMascara(String modelo, String uso, String recondicionamento, short ciclos) {
        this.modelo = modelo;
        this.uso = uso;
        this.recondicionamento = recondicionamento;
        this.ciclos = ciclos;
    }
}
