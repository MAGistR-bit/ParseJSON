package mag.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    /**
     * Сокращенное название пункта вылета
     */
    private String origin;
    /**
     * Полное название пункта вылета
     */
    private String origin_name;
    /**
     * Сокращенное название пункта назначения
     */
    private String destination;
    /**
     * Полное название пункта назначения
     */
    private String destination_name;
    /**
     * Дата вылета
     */
    private String departure_date;
    /**
     * Время отправления
     */
    private String departure_time;
    /**
     * Дата прибытия
     */
    private String arrival_date;
    /**
     * Время прибытия
     */
    private String arrival_time;
    /**
     * Перевозчик
     */
    private String carrier;
    /**
     * Остановки
     */
    private int stops;
    /**
     * Стоимость билета
     */
    private double price;
}
