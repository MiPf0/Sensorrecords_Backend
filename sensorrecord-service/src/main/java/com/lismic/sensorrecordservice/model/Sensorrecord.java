package com.lismic.sensorrecordservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection="sensorrecord")
public class Sensorrecord {

    @Id
    private ObjectId id;
    private String sensorId; //aplhanumeric
    private String temperature;
    private String humidity;
}
