package com.mps.MPSServer.classification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertClassification {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private short alertClassificationId;

    @Column(columnDefinition = "varchar(64)")
    private String alertName;

    private int severity;

    public AlertClassification(String alertName, int severity) {
        this.alertName = alertName;
        this.severity = severity;
    }
}
