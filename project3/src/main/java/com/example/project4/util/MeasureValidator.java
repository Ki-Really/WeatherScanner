package com.example.project4.util;

import com.example.project4.models.Measure;
import com.example.project4.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class MeasureValidator implements Validator {
    private final SensorsService sensorsService;
    @Autowired
    public MeasureValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measure.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measure measure = (Measure) target;
        if (measure.getSensor() == null) {
            return;
        }
        if(sensorsService.findBySensorsName(measure.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor","","There is no sensor with such name!");
        }
    }
}
