package maxim.spring.restapp.services;

import maxim.spring.restapp.models.Measurement;
import maxim.spring.restapp.models.Sensor;
import maxim.spring.restapp.repositories.SensorRepository;
import maxim.spring.restapp.util.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor getSensorByName(String name) {
        return sensorRepository.findByName(name).orElse(null);
    }

    public Sensor getSensorByNameOrThrow(String name) {
        return sensorRepository.findByName(name).orElseThrow(SensorNotFoundException::new);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }


}
