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
public class PanelModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long panelModelId;

    private String panelModelName;

    private float power;

    private float nominalVoltage;

    private float nominalCurrent;

    public PanelModel(String panelModelName, float power, float nominalVoltage, float nominalCurrent) {
        this.panelModelName = panelModelName;
        this.power = power;
        this.nominalVoltage = nominalVoltage;
        this.nominalCurrent = nominalCurrent;
    }
}
