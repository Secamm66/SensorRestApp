package ru.ershov.project.SensorRestApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ershov.project.SensorRestApp.dto.MeasurementDTO;
import ru.ershov.project.SensorRestApp.dto.MeasurementsResponse;
import ru.ershov.project.SensorRestApp.services.MeasurementService;
import ru.ershov.project.SensorRestApp.util.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementsController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping()
    public MeasurementsResponse getAllMeasurements() {
        return measurementService.getMeasurementToSend();
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        measurementService.addMeasurement(measurementDTO, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
