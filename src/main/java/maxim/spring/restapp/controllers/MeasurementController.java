package maxim.spring.restapp.controllers;

import jakarta.validation.Valid;
import maxim.spring.restapp.dto.MeasurementDTO;
import maxim.spring.restapp.models.Measurement;
import maxim.spring.restapp.services.MeasurementService;
import maxim.spring.restapp.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errors.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("; ");
            }

            throw new MeasurementNotCreatedException(errors.toString());

        }

        measurementService.save(convertMeasurementDTOToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll()
                .stream()
                .map(this::convertMeasurementToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.countRainyDays();
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e) {
        SensorErrorResponse response = new SensorErrorResponse("Сенсор не найден");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private Measurement convertMeasurementDTOToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertMeasurementToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }


}
