package ru.ershov.project.SensorRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ershov.project.SensorRestApp.models.Sensor;
import ru.ershov.project.SensorRestApp.repositories.SensorRepository;

@Component
public class SensorValidator implements Validator {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;
        if (sensorRepository.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "", "Сенсор с таким именем существует");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }
}
