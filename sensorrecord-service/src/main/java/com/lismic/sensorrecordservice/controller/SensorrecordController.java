package com.lismic.sensorrecordservice.controller;

import com.lismic.sensorrecordservice.model.Sensorrecord;
import com.lismic.sensorrecordservice.repository.SensorrecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
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

    @GetMapping("/sensorrecords/findNewestSensorrecord")
    //@CrossOrigin(origins = "localhost:3000")
    public ResponseEntity<?> getNewestSensorrecord() {
        Sensorrecord newestSensorrecord = new Sensorrecord();
        newestSensorrecord.setTimestamp("631177200"); //timestamp set to 01.01.1990
        List<Sensorrecord> allRecords = repository.findAll();
        for (Sensorrecord sr : allRecords) {
            if (Integer.parseInt(sr.getTimestamp()) > Integer.parseInt(newestSensorrecord.getTimestamp())) {
                newestSensorrecord = sr;
            }
        }
        String relevantSensorrecordId = newestSensorrecord.getId();
        log.info("Inside updateNewestSensorrecord method of SensorrecordController");
        return new ResponseEntity<>(repository.findById(relevantSensorrecordId), HttpStatus.OK);
    }

    @GetMapping("/sensorrecords/findNewestSensorrecord/sensors/{sensorId}")
    //@CrossOrigin(origins = "localhost:3000")
    public ResponseEntity<?> getNewestSensorrecordBySensorId(@PathVariable String sensorId) {
        Sensorrecord newestSensorrecord = new Sensorrecord();
        newestSensorrecord.setTimestamp("631177200"); //timestamp set to 01.01.1990
        List<Sensorrecord> allRecords = repository.findAll();
        List<Sensorrecord> recordsBySensorId = new ArrayList<>();
        for (Sensorrecord sr : allRecords) {
            if (sr.getSensorId().equals(sensorId)) {
                recordsBySensorId.add(sr);
            }
        }
        for (Sensorrecord sr : recordsBySensorId) {
            if (Integer.parseInt(sr.getTimestamp()) > Integer.parseInt(newestSensorrecord.getTimestamp())) {
                newestSensorrecord = sr;
            }
        }
        String relevantSensorrecordId = newestSensorrecord.getId();
        log.info("Inside updateNewestSensorrecord method of SensorrecordController");
        return new ResponseEntity<>(repository.findById(relevantSensorrecordId), HttpStatus.OK);
    }

    @GetMapping("/sensorrecords/findNewestSensorrecords/{num}")
    //@CrossOrigin(origins = "localhost:3000")
    public List<Sensorrecord> getNewestSensorrecords(@PathVariable int num) {
        List<Sensorrecord> allRecords = repository.findAll();
        allRecords.sort(Comparator.comparing(Sensorrecord::getTimestamp).reversed());
        List<Sensorrecord> newestRecords = new ArrayList<>();
        int i = 0;
        for (Sensorrecord sr : allRecords) {
            newestRecords.add(sr);
            i++;
            if (i == num) {
                break;
            }
        }
        return newestRecords;
    }

    @GetMapping("/sensorrecords/findNewestSensorrecords/{num}/sensors/{sensorId}")
    //@CrossOrigin(origins = "localhost:3000")
    public List<Sensorrecord> getNewestSensorrecordsBySensorId(@PathVariable int num, @PathVariable String sensorId) {
        List<Sensorrecord> allRecords = repository.findAll();
        allRecords.sort(Comparator.comparing(Sensorrecord::getTimestamp).reversed());
        List<Sensorrecord> recordsBySensorId = new ArrayList<>();
        for (Sensorrecord sr : allRecords) {
            if (sr.getSensorId().equals(sensorId)) {
                recordsBySensorId.add(sr);
            }
        }
        List<Sensorrecord> newestRecordsBySensorId = new ArrayList<>();
        int i = 0;
        for (Sensorrecord sr : recordsBySensorId) {
            newestRecordsBySensorId.add(sr);
            i++;
            if (i == num) {
                break;
            }
        }
        return newestRecordsBySensorId;
    }

    @GetMapping("/sensorrecords/findNewestSensorrecords/hours/{hours}")
    //@CrossOrigin(origins = "localhost:3000")
    public List<Sensorrecord> getNewestSensorrecordsByHours(@PathVariable long hours) {

        long currentTimestamp = System.currentTimeMillis() / 1000L;
        long limitTimestamp = currentTimestamp-(hours*60*60);

        List<Sensorrecord> allRecords = repository.findAll();
        List<Sensorrecord> recordsByHoursLimitation = new ArrayList<>();

        for (Sensorrecord sr : allRecords) {
            if (Integer.parseInt(sr.getTimestamp()) > limitTimestamp) {
                recordsByHoursLimitation.add(sr);
            }
        }
        return recordsByHoursLimitation;
    }

    @GetMapping("/sensorrecords/findNewestSensorrecords/hours/{hours}/sensors/{sensorId}")
    //@CrossOrigin(origins = "localhost:3000")
    public List<Sensorrecord> getNewestSensorrecordsByHoursBySensorId(@PathVariable long hours, @PathVariable String sensorId) {
        long currentTimestamp = System.currentTimeMillis() / 1000L;
        long limitTimestamp = currentTimestamp-(hours*60*60);
        List<Sensorrecord> allRecords = repository.findAll();
        List<Sensorrecord> recordsBySensorId = new ArrayList<>();
        for (Sensorrecord sr : allRecords) {
            if (sr.getSensorId().equals(sensorId)) {
                recordsBySensorId.add(sr);
            }
        }
        List<Sensorrecord> recordsByHoursLimitation = new ArrayList<>();
        for (Sensorrecord sr : recordsBySensorId) {
            if (Integer.parseInt(sr.getTimestamp()) > limitTimestamp) {
                recordsByHoursLimitation.add(sr);
            }
        }
        return recordsByHoursLimitation;
    }

    @GetMapping("/sensorrecords/findSensorrecord/{id}")
    //@CrossOrigin(origins = "localhost:3000")
    public Optional<Sensorrecord> getSensorrecord(@PathVariable String id) {
        log.info("Inside getSensorrecord method of SensorrecordController");
        return repository.findById(id);
    }

    @GetMapping("/sensorrecords/findSensorrecords/sensors/{sensorId}")
    //@CrossOrigin(origins = "localhost:3000")
    public List<Sensorrecord> getSensorrecordsBySensorId(@PathVariable String sensorId) {
        log.info("Inside getSensorrecord method of SensorrecordController");
        return repository.findSensorrecordsBySensorId(sensorId);
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
            log.info("Inside updateSensorrecord method of SensorrecordController");
            return new ResponseEntity<>(repository.save(_sensorrecord), HttpStatus.OK);
        } else {
            log.info("Inside updateSensorrecord method of SensorrecordController");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/sensorrecords/updateNewestSensorrecord/{id}")
    //@CrossOrigin(origins = "localhost:3000")
    public ResponseEntity<?> updateNewestSensorrecord(@PathVariable("id") String id, @RequestBody Sensorrecord sensorrecord) {
        Optional<Sensorrecord> optionalSensorrecord = repository.findById(id);
        if (optionalSensorrecord.isPresent()) {
            String relevantSensorId = sensorrecord.getSensorId();
            Sensorrecord newestSensorrecord = new Sensorrecord();
            newestSensorrecord.setTimestamp("631177200"); //timestamp set to 01.01.1990
            List<Sensorrecord> allRecords = repository.findAll();
            for (Sensorrecord sr : allRecords) {
                if (sr.getSensorId().equals(relevantSensorId)) {
                    if (Integer.parseInt(sr.getTimestamp())>Integer.parseInt(newestSensorrecord.getTimestamp())) {
                        newestSensorrecord = sr;
                    }
                }
            }
            String relevantSensorrecordId = newestSensorrecord.getId();
            log.info("Inside updateNewestSensorrecord method of SensorrecordController");
            return new ResponseEntity<>(repository.findById(relevantSensorrecordId), HttpStatus.OK);
        } else {
            log.info("Inside updateNewestSensorrecord method of SensorrecordController");
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