package com.lismic.sensorrecordservice.controller;

import com.lismic.sensorrecordservice.model.Sensorrecord;
import com.lismic.sensorrecordservice.repository.SensorrecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@Slf4j
public class SensorrecordController {

    @Autowired
    private SensorrecordRepository repository;

    @PostMapping("/sensorrecords/addSensorrecord")
    //@CrossOrigin(origins = "localhost:3000")
    public ResponseEntity<Sensorrecord> saveSensorrecord(@RequestBody Sensorrecord sensorrecord) {
        repository.save(sensorrecord);
        log.info("Inside saveSensorrecord method of SensorrecordController");
        return new ResponseEntity<>(sensorrecord, HttpStatus.CREATED);
    }

    @GetMapping("/sensorrecords/findAllSensorrecords")
    //@CrossOrigin(origins = "localhost:3000")
    public List<Sensorrecord> getSensorrecords() {
        log.info("Inside getSensorrecords method of SensorrecordController");
        System.out.println("accessed");
        return repository.findAll();
    }

    @GetMapping("/sensorrecords/findSensorrecord/{id}")
    //@CrossOrigin(origins = "localhost:3000")
    public Optional<Sensorrecord> getSensorrecord(@PathVariable String id) {
        log.info("Inside getSensorrecord method of SensorrecordController");
        return repository.findById(id);
    }

    @GetMapping("/sensorrecords/findSensorrecords/sensors/{sensorId}")
    //@CrossOrigin(origins = "localhost:3000")
    public List<Sensorrecord> getSensorrecords(@PathVariable String sensorId) {
        log.info("Inside getSensorrecord method of SensorrecordController");
        return repository.findSensorrecordsBySensorId(sensorId);
    }

    @GetMapping("/sensorrecords/findNewestEntryPerSensorId")
    //@CrossOrigin(origins = "localhost:3000")
    public List<Sensorrecord> findNewestEntryPerSensorId() {
        List<Sensorrecord> allRecords = repository.findAll();
        allRecords.sort(Comparator.comparing(Sensorrecord::getTimestamp));
        allRecords.sort(Comparator.comparing(Sensorrecord::getSensorId));

        /*String currentSensorId = "";

        for (Sensorrecord sr : allRecords) {
            if (sr.getSensorId().equals(currentSensorId)) {
                allRecords.remove(sr);
            }
            currentSensorId = sr.getSensorId();
        }*/

        return allRecords;
    }

    @GetMapping("/sensorrecords/loadBalancingTest")
    public String testService(HttpServletRequest request) {
        System.out.println("I am " + request.getRequestURL().toString());
        return request.getRequestURL().toString();
    }

    @PutMapping("/sensorrecords/updateSensorrecord/{id}")
    //@CrossOrigin(origins = "localhost:3000")
    public ResponseEntity<Sensorrecord> updateSensorrecord(@PathVariable("id") String id, @RequestBody Sensorrecord sensorrecord) {
        Optional<Sensorrecord> optionalSensorrecord = repository.findById(id);

        if (optionalSensorrecord.isPresent()) {
            Sensorrecord _sensorrecord = optionalSensorrecord.get();
            _sensorrecord.setDate(sensorrecord.getDate());
            _sensorrecord.setTimestamp(sensorrecord.getTimestamp());
            _sensorrecord.setSensorId(sensorrecord.getSensorId());
            _sensorrecord.setTemperature(sensorrecord.getTemperature());
            _sensorrecord.setHumidity(sensorrecord.getHumidity());
            _sensorrecord.setShowData(sensorrecord.isShowData());
            return new ResponseEntity<>(repository.save(_sensorrecord), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/sensorrecords/deleteSensorrecord/{id}")
    //@CrossOrigin(origins = "localhost:3000")
    public ResponseEntity<?> deleteSensorrecord(@PathVariable String id) {
        repository.deleteById(id);
        log.info("Inside deleteSensorrecord method of SensorrecordController");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}