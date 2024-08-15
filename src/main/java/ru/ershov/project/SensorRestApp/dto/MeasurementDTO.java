package ru.ershov.project.SensorRestApp.dto;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull(message = "Значение value не может быть пустым")
    @Min(value = -100, message = "Значение температуры не может быть ниже -100")
    @Max(value = 100, message = "Значение температуры не может быть выше +100")
    @Column(name = "value")
    private Float value;

    @NotNull(message = "Значение raining не может быть пустым")
    @Column(name = "raining")
    private Boolean raining;

    @NotNull(message = "Ответ должен передавать объект Sensor")
    private SensorDTO sensor;

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

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
