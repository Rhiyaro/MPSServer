package com.mps.MPSServer.classification;

import com.mps.MPSServer.domain.Panel;
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
public class ClassificationAlert {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long classificationAlertId;

    @Column(name = "alert_ts")
    private Date alertTS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelTrainingId", referencedColumnName = "modelTrainingId")
    private ModelTraining modelTraining;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panelId", referencedColumnName = "panelId")
    private Panel panel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alertClassificationId", referencedColumnName = "alertClassificationId")
    private AlertClassification alertClassification;

    private boolean alert;

    private boolean notified;
}
