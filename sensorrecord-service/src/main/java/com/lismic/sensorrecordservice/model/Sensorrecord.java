package com.lismic.sensorrecordservice.model;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection="sensorrecord")
public class Sensorrecord {
    @Id
    private String objectId = String.valueOf(new ObjectId());
    @CreatedDate
    private String date = String.valueOf(new ObjectId().getDate());
    private String timestamp = String.valueOf(new ObjectId().getTimestamp());
    private String sensorId; //aplhanumeric
    private String temperature;
    private String humidity;
    private boolean showData = false;
}