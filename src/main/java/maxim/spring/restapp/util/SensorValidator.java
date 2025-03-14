package maxim.spring.restapp.util;

import maxim.spring.restapp.models.Sensor;
import maxim.spring.restapp.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        Sensor existingSensor = sensorService.getSensorByName(sensor.getName());
        if (existingSensor != null) {
            errors.rejectValue("name", "", "Сенсор с таким именем уже есть");
        }
    }
}
