package com.lismic.sensorrecordservice.repository;

import com.lismic.sensorrecordservice.model.Sensorrecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorrecordRepository extends MongoRepository<Sensorrecord, String> {
    Optional<Sensorrecord> findById(int id);
    List<Sensorrecord> findSensorrecordsBySensorId(String sensorId);
    void deleteById(int id);
}