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
public class FailureEvent {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long failureEventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panelId", referencedColumnName = "panelId")
    private Panel panel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alertClassificationId", referencedColumnName = "alertClassificationId")
    private AlertClassification alertClassification;

    @Column(name = "ts_start")
    private Date startTS;

    @Column(name = "ts_end")
    private Date endTS;

    private String comment;
}
