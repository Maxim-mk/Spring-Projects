package maxim.spring.restapp.testing;

import maxim.spring.restapp.dto.MeasurementDTO;
import maxim.spring.restapp.dto.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class Sender {
    private static final String BASE_URL = "http://localhost:8080/measurements/add";
    private static final RestTemplate restTemplate = new RestTemplate();


    public static void main(String[] args) {
        sendRandomMeasurements(1000);
    }

    public static void sendRandomMeasurements(int numberOfRequests) {
        Random random = new Random();

        for (int i = 0; i < numberOfRequests; i++) {
            float value = -50 + (100 - (-50)) * random.nextFloat(); // Температура от -50 до 100
            boolean raining = random.nextBoolean(); // Случайное значение raining
            String sensorName = "Sensor 5";

            // Создание DTO
            MeasurementDTO measurementDTO = new MeasurementDTO();
            measurementDTO.setValue(value);
            measurementDTO.setRaining(raining);

            SensorDTO sensorDTO = new SensorDTO();
            sensorDTO.setName(sensorName);
            measurementDTO.setSensor(sensorDTO);


            // Отправка POST-запроса
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<MeasurementDTO> request = new HttpEntity<>(measurementDTO, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, request, String.class);

            // Логирование
            System.out.println("Request " + (i + 1) + ": " + measurementDTO);
            System.out.println("Response: " + response.getBody());
        }
    }
}
