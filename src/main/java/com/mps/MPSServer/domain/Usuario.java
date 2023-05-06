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
public class Usuario {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idUsuario;

    @Column(columnDefinition = "varchar(64)")
    private String nome;

    @Column(columnDefinition = "varchar(16)")
    private String cpf;

    @Column(columnDefinition = "varchar(64)")
    private String endereco;

    @Column(columnDefinition = "varchar(32)")
    private String cidade;

    @Column(columnDefinition = "enum('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO')")
    private String estado;

    @Column(columnDefinition = "varchar(32)")
    private String telefone;

    @Column(columnDefinition = "varchar(64)")
    private String localTrabalho;

    public Usuario(String nome, String cpf, String endereco, String cidade, String estado, String telefone, String localTrabalho) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.localTrabalho = localTrabalho;
    }
}
