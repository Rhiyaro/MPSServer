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
public class MPSUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long userId;

    @Column(columnDefinition = "varchar(64)")
    private String name;

    @Column(columnDefinition = "varchar(16)")
    private String cpf;

    @Column(columnDefinition = "varchar(32)")
    private String phone;

    @Column(columnDefinition = "varchar(128)")
    private String email;

    public MPSUser(String name, String cpf, String phone, String email) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
    }
}
