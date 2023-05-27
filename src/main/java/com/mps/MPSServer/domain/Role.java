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
public class Role {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long roleId;
//    @Column(columnDefinition = "varchar(16)")
    String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE/*, CascadeType.PERSIST*/},
            mappedBy = "roles")
    @JsonIgnore
    List<UserCredential> credentials = new ArrayList<>();

    public Role(String name) {
        this.name = name;
    }
}
