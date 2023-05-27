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
public class Panel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long panelId;

    private String panelName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panelModelId", referencedColumnName = "panelModelId")
    private PanelModel panelModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parkId", referencedColumnName = "parkId")
    private Park park;

    public Panel(String panelName, PanelModel panelModel, Park park) {
        this.panelName = panelName;
        this.panelModel = panelModel;
        this.park = park;
    }
}
