package ru.ershov.project.SensorRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ershov.project.SensorRestApp.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}

