package maxim.spring.restapp.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @Min(value = -100, message = "Значение не может быть меньше -100")
    @Max(value = 100, message = "Значение не может быть больше 100")
    @NotNull(message = "Пустое поле температуры")
    private Float value;

    @NotNull(message = "Пустое поле наличия дождя")
    @Column(name = "raining")
    private Boolean raining;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne()
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @Valid
    private Sensor sensor;

    @Column(name = "sensor_name")
    private String dbSensorName;

    public Measurement() {
    }

    public Measurement(float value, boolean raining, LocalDateTime time) {
        this.value = value;
        this.raining = raining;
        this.time = time;
    }

    public String getDbSensorName() {
        return dbSensorName;
    }

    public void setDbSensorName(String dbSensorName) {
        this.dbSensorName = dbSensorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
