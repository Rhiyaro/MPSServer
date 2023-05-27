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
public class Channel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long channelId;

    private String channelName;

    private String description;

    private String type;

    private String unit;

    public Channel(String channelName, String description, String type, String unit) {
        this.channelName = channelName;
        this.description = description;
        this.type = type;
        this.unit = unit;
    }
}
