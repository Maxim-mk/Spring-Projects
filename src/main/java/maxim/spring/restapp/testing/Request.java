package maxim.spring.restapp.testing;

import maxim.spring.restapp.dto.MeasurementDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Request {
    private static final String BASE_URL = "http://localhost:8080/measurements";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        List<MeasurementDTO> measurements = getAllMeasurements();
        System.out.println("Received " + measurements.size() + " measurements:");
        measurements.forEach(System.out::println);
    }

    public static List<MeasurementDTO> getAllMeasurements() {
        ResponseEntity<MeasurementDTO[]> response = restTemplate.getForEntity(BASE_URL, MeasurementDTO[].class);

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
