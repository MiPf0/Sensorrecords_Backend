package com.lismic.sensorrecordservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection="sensorrecord")
public class Sensorrecord {

    @Id
    private ObjectId objectId;
    private String sensorId; //aplhanumeric
    private String temperature;
    private String humidity;
    private boolean showData = false;

}