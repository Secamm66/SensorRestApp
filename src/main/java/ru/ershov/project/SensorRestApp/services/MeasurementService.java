package ru.ershov.project.SensorRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.ershov.project.SensorRestApp.dto.MeasurementDTO;
import ru.ershov.project.SensorRestApp.dto.MeasurementsResponse;
import ru.ershov.project.SensorRestApp.models.Measurement;
import ru.ershov.project.SensorRestApp.repositories.MeasurementRepository;
import ru.ershov.project.SensorRestApp.repositories.SensorRepository;
import ru.ershov.project.SensorRestApp.util.ErrorsUtil;
import ru.ershov.project.SensorRestApp.util.MeasurementValidator;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final MeasurementValidator measurementValidator;
    private final ConvertService convertService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository,
                              SensorRepository sensorRepository,
                              MeasurementValidator measurementValidator, ConvertService convertService) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
        this.measurementValidator = measurementValidator;
        this.convertService = convertService;
    }

    public List<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }

    public MeasurementsResponse getMeasurementToSend() {
        return new MeasurementsResponse(getMeasurements()
                .stream().map(convertService::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    public Long getRainyDaysCount() {
        return getMeasurements().stream().filter(Measurement::isRaining).count();
    }

    @Transactional
    public void addMeasurement(MeasurementDTO measurementDTO, BindingResult bindingResult) {
        Measurement measurementToAdd = convertService.convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        } else {
            save(measurementToAdd);
        }
    }

    private void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorRepository.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasured_at(new Date());
    }
}
