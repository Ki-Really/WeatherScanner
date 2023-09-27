package com.example.project4.controllers;

import com.example.project4.dto.MeasureDTO;
import com.example.project4.dto.MeasureResponse;
import com.example.project4.models.Measure;
import com.example.project4.services.MeasuresService;
import com.example.project4.util.MeasureErrorResponse;
import com.example.project4.util.MeasureException;
import com.example.project4.util.MeasureValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measures")
public class MeasurementsController {
    private final MeasuresService measuresService;

    private final ModelMapper modelMapper;
    private final MeasureValidator measureValidator;
    @Autowired
    public MeasurementsController(MeasuresService measuresService, ModelMapper modelMapper, MeasureValidator measureValidator) {
        this.measuresService = measuresService;
        this.modelMapper = modelMapper;
        this.measureValidator = measureValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasureDTO measureDTO,
                                          BindingResult bindingResult){
        System.out.println("111");
        Measure measureToAdd = convertToMeasure(measureDTO);
        measureValidator.validate(measureToAdd,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMsg.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
            }
        }

        measuresService.save(measureToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public MeasureResponse getMeasures(){
        return new MeasureResponse(measuresService.findAll().stream()
                .map(this::convertToMeasureDTO).collect(Collectors.toList()));
    }

    /*@GetMapping(name="/{id}")
    public MeasureDTO getMeasure(@PathVariable int id){
        return convertToMeasureDTO(measuresService.findOne(id));
    }*/

    @ExceptionHandler
    private ResponseEntity<MeasureErrorResponse> handleException(MeasureException e) {
        MeasureErrorResponse response = new MeasureErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measure convertToMeasure(MeasureDTO measureDTO){
        return modelMapper.map(measureDTO,Measure.class);
    }

    private MeasureDTO convertToMeasureDTO(Measure measure){
        return modelMapper.map(measure,MeasureDTO.class);
    }
}
