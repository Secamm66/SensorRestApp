package ru.ershov.project.SensorRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ershov.project.SensorRestApp.models.Measurement;
import ru.ershov.project.SensorRestApp.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;
        if (sensorService.getSensorByName(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "", "Сенсор с таким именем не зарегистрирован в системе");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }
}
