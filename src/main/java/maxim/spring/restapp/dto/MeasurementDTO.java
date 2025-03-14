package maxim.spring.restapp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class MeasurementDTO {

    @NotNull(message = "Пустое поле температуры")
    @Min(value = -100, message = "Значение не может быть меньше -100")
    @Max(value = 100, message = "Значение не может быть больше 100")
    private Float value;

    @NotNull(message = "Пустое поле наличия дождя")
    private Boolean raining;

    @NotNull(message = "Пустое имя сенсора")
    @Valid
    private SensorDTO sensor;


    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
