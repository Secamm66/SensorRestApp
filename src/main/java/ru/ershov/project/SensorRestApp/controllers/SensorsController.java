package ru.ershov.project.SensorRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ershov.project.SensorRestApp.dto.SensorDTO;
import ru.ershov.project.SensorRestApp.services.SensorService;
import ru.ershov.project.SensorRestApp.util.MeasurementNotCreatedException;
import ru.ershov.project.SensorRestApp.util.SensorErrorResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorService sensorService;
    
    @Autowired
    public SensorsController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        sensorService.createSensor(sensorDTO, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(MeasurementNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
