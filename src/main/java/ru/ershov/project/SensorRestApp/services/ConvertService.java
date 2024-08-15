package ru.ershov.project.SensorRestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ershov.project.SensorRestApp.dto.MeasurementDTO;
import ru.ershov.project.SensorRestApp.dto.SensorDTO;
import ru.ershov.project.SensorRestApp.models.Measurement;
import ru.ershov.project.SensorRestApp.models.Sensor;

@Service
public class ConvertService {

    private final ModelMapper modelMapper;

    public ConvertService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
