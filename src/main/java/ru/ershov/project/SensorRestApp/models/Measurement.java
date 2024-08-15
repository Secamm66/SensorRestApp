package ru.ershov.project.SensorRestApp.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Значение value не может быть пустым")
    @Min(value = -100, message = "Значение температуры не может быть ниже -100")
    @Max(value = 100, message = "Значение температуры не может быть выше +100")
    @Column(name = "value")
    private Float value;

    @NotNull(message = "Значение raining не может быть пустым")
    @Column(name = "raining")
    private Boolean raining;

    @Column(name = "measured_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date measured_at;

    @NotNull(message = "Ответ должен передавать объект Sensor")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement(Float value, Boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Measurement() {

    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getMeasured_at() {
        return measured_at;
    }

    public void setMeasured_at(Date measured_at) {
        this.measured_at = measured_at;
    }
}
