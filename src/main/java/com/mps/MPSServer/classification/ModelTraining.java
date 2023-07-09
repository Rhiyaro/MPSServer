package com.mps.MPSServer.classification;

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
public class ModelTraining {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int modelTrainingId;

    @Column(columnDefinition = "varchar(32)")
    private String trainingLevel;

    @Column(columnDefinition = "varchar(128)")
    private String trainingIds;

    @Column(columnDefinition = "varchar(128)")
    private String inputs;

    @Column(columnDefinition = "varchar(256)")
    private String scalerData;

    @Column(columnDefinition = "varchar(64)")
    private String labelData;

    @Column(name="ts_train")
    private Date ts_train;

    @Column(name="ts_start")
    private Date ts_start;

    @Column(name="ts_end")
    private Date ts_end;

    public ModelTraining(String trainingLevel, String trainingIds, String inputs, String scalerData, String labelData, Date ts_train, Date ts_start, Date ts_end) {
        this.trainingLevel = trainingLevel;
        this.trainingIds = trainingIds;
        this.inputs = inputs;
        this.scalerData = scalerData;
        this.labelData = labelData;
        this.ts_train = ts_train;
        this.ts_start = ts_start;
        this.ts_end = ts_end;
    }
}
