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
public class Park {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long parkId;

    private String parkName;

    private float parkLat;
    private float parkLong;

    public Park(String parkName, float parkLat, float parkLong) {
        this.parkName = parkName;
        this.parkLat = parkLat;
        this.parkLong = parkLong;
    }
}
