package maxim.spring.restapp.services;

import maxim.spring.restapp.models.Measurement;
import maxim.spring.restapp.models.Sensor;
import maxim.spring.restapp.repositories.MeasurementRepository;
import maxim.spring.restapp.util.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(final MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setTime(LocalDateTime.now());

        String sensorName = measurement.getSensor().getName();
        Sensor sensor = sensorService.getSensorByNameOrThrow(sensorName);

        measurement.setSensor(sensor);
        measurement.setDbSensorName(sensorName);

        measurementRepository.save(measurement);

    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public int countRainyDays() {
        return measurementRepository.countByRainingIsTrue();
    }


}
