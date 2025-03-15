package maxim.spring.restapp.testing;

import maxim.spring.restapp.dto.MeasurementDTO;
import org.knowm.xchart.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    private static final String BASE_URL = "http://localhost:8080/measurements";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        // Получаем данные с сервера
        List<MeasurementDTO> measurements = getMeasurementsFromServer();

        // Извлекаем температуры
        List<Float> temperatures = new ArrayList<>();
        for (MeasurementDTO measurement : measurements) {
            temperatures.add(measurement.getValue());
        }
        // Для наглядности берутся последние 15 значений
        if (temperatures.size() > 15) {
            temperatures = temperatures.subList(temperatures.size() - 15, temperatures.size());
        }

        // Строим график
        buildTemperatureChart(temperatures);
    }


    public static List<MeasurementDTO> getMeasurementsFromServer() {
        ResponseEntity<MeasurementDTO[]> response = restTemplate.getForEntity(BASE_URL, MeasurementDTO[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            throw new RuntimeException("Failed to fetch measurements: " + response.getStatusCode());
        }
    }

    /**
     * Строит график температур.
     *
     * @param temperatures Список температур.
     */
    public static void buildTemperatureChart(List<Float> temperatures) {
        // Создаем график
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Колебания температуры на 15 последних значениях")
                .xAxisTitle("Измерения")
                .yAxisTitle("Температура (°C)")
                .build();

        // Добавляем данные на график
        List<Integer> xData = new ArrayList<>();
        for (int i = 0; i < temperatures.size(); i++) {
            xData.add(i + 1);
        }

        chart.addSeries("Температура", xData, temperatures);

        // Отображаем график
        new SwingWrapper<>(chart).displayChart();

        // Сохраняем график в файл
        try {
            BitmapEncoder.saveBitmap(chart, "last_15_temperatures.png", BitmapEncoder.BitmapFormat.PNG);
            System.out.println("Chart saved to last_15_temperatures.png");
        } catch (Exception e) {
            System.err.println("Failed to save chart: " + e.getMessage());
        }
    }
}
