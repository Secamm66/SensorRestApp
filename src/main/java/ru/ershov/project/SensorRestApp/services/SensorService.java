package ru.ershov.project.SensorRestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.ershov.project.SensorRestApp.dto.SensorDTO;
import ru.ershov.project.SensorRestApp.models.Sensor;
import ru.ershov.project.SensorRestApp.repositories.SensorRepository;
import ru.ershov.project.SensorRestApp.util.ErrorsUtil;
import ru.ershov.project.SensorRestApp.util.SensorValidator;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    private final SensorValidator sensorValidator;
    private final ConvertService convertService;

    @Autowired
    public SensorService(SensorRepository sensorRepository, SensorValidator sensorValidator,
                         ConvertService convertService) {
        this.sensorRepository = sensorRepository;
        this.sensorValidator = sensorValidator;
        this.convertService = convertService;
    }

    @Transactional
    public void createSensor(SensorDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensorToAdd = convertService.convertToSensor(sensorDTO);
        sensorValidator.validate(sensorToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        } else {
            save(sensorToAdd);
        }
    }

    private void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorRepository.findByName(name);
    }
}
