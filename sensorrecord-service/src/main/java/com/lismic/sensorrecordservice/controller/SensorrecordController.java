package com.lismic.sensorrecordservice.controller;

import com.lismic.sensorrecordservice.model.Sensorrecord;
import com.lismic.sensorrecordservice.repository.SensorrecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class SensorrecordController {

    @Autowired
    private SensorrecordRepository repository;

    @PostMapping("/sensorrecords/addSensorrecord")
    public String saveSensorrecord(@RequestBody Sensorrecord sensorrecord) {
        repository.save(sensorrecord);
        log.info("Inside saveSensorrecord method of SensorrecordController");
        return "Added sensor record with id : " + sensorrecord.getObjectId() + "; with Timestamp: " + sensorrecord.getObjectId().getTimestamp();
    }

    @GetMapping("/sensorrecords/findAllSensorrecords")
    public List<Sensorrecord> getSensorrecords() {
        log.info("Inside getSensorrecords method of SensorrecordController");
        return repository.findAll();
    }

    @GetMapping("/sensorrecords/findSensorrecord/{id}")
    public Optional<Sensorrecord> getSensorrecord(@PathVariable String id) {
        log.info("Inside getSensorrecord method of SensorrecordController");
        return repository.findById(id);
    }

    @GetMapping("/sensorrecords/findSensorrecords/sensors/{sensorId}")
    public List<Sensorrecord> getSensorrecords(@PathVariable String sensorId) {
        log.info("Inside getSensorrecord method of SensorrecordController");
        return repository.findSensorrecordsBySensorId(sensorId);
    }

    @DeleteMapping("/sensorrecords/deleteSensorrecord/{id}")
    public String deleteSensorrecord(@PathVariable String id) {
        repository.deleteById(id);
        log.info("Inside deleteSensorrecord method of SensorrecordController");
        return "Sensorrecord deleted with id: " + id;
    }

}