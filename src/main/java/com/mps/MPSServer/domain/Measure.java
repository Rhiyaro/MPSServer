package com.mps.MPSServer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measure {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long measureId;

    private Date TS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panelId", referencedColumnName = "panelId")
    private Panel panel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channelId", referencedColumnName = "channelId")
    private Channel channel;

    private float value;

    public Measure(Date TS, Panel panel, Channel channel, float value) {
        this.TS = TS;
        this.panel = panel;
        this.channel = channel;
        this.value = value;
    }
}
