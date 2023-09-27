package org.example;

import org.example.dto.MeasureResponse;

import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChartDrawing {
    public static void main(String[] args) {
        List<Float> temperatures = getTemperaturesFromServer();

        for(int i = 0; i<temperatures.size();i++){
            System.out.println(temperatures.get(i));
        }
    }

    private static List<Float> getTemperaturesFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measures";

        MeasureResponse jsonResponse = restTemplate.getForObject(url, MeasureResponse.class);

        if (jsonResponse == null || jsonResponse.getMeasureList() == null)
            return Collections.emptyList();

        return jsonResponse.getMeasureList().stream().map(measureDTO -> measureDTO.getValue()).collect(Collectors.toList());
    }
}
