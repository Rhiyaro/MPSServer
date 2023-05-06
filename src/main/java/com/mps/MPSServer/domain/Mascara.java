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
public class Mascara {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idMascara;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idModeloMascara", referencedColumnName = "idModeloMascara")
    private ModeloMascara modeloMascara;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
    private String apelido;
    @Column(columnDefinition = "smallint default 0")
    private short ciclos;

    public Mascara(ModeloMascara modeloMascara, Usuario usuario, String apelido, short ciclos) {
        this.modeloMascara = modeloMascara;
        this.usuario = usuario;
        this.apelido = apelido;
        this.ciclos = ciclos;
    }

    public Mascara(ModeloMascara modeloMascara, Usuario usuario, String apelido) {
        this.modeloMascara = modeloMascara;
        this.usuario = usuario;
        this.apelido = apelido;
        this.ciclos = 0;
    }

}
