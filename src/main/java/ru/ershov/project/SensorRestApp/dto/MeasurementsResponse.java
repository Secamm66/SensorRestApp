package ru.ershov.project.SensorRestApp.dto;

import java.util.List;

public class MeasurementsResponse {

    private List<MeasurementDTO> measurements;

    public MeasurementsResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurementsDTO(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
