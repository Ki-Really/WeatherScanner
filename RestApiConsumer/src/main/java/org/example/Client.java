package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client
{
    public static void main( String[] args )
    {
        String sensorName = "Sensor1";
       // registerSensor(sensorName);
        Random random = new Random();

        int minTemperature = 0;
        int maxTemperature = 45;
        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            sendMeasurement(random.nextInt(45)+1,
                    random.nextBoolean(), sensorName);
        }
    }
   /* private static void registerSensor(String name){
        final String url = "http://localhost:8080/sensors/registration";

        Map<String,Object> jsonData = new HashMap<String, Object>();
        jsonData.put("name", name);

        makePostRequestWithJSONData(url, jsonData);
    }*/

    private static void sendMeasurement(double value, boolean raining, String sensorName) {
        final String url = "http://localhost:8080/measures/add";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));

        makePostRequestWithJSONData(url, jsonData);
    }

    private static void makePostRequestWithJSONData(String url,Map<String,Object> jsonData){
        final RestTemplate restTemplate = new RestTemplate();
        /*restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());*/

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<Object>(jsonData, headers);


        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("Измерение успешно отправлено на сервер!");
        } catch (HttpClientErrorException e) {
            System.out.println("ОШИБКА!");
            System.out.println(e.getMessage());
        }
    }
}
