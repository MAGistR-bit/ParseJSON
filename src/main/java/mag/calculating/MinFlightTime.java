package mag.calculating;

import mag.model.Ticket;
import mag.pojo.TicketPOJO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author mikhail
 * @version 1.0.0
 */
public class MinFlightTime {

    /**
     * Форматирование даты
     */
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
    /**
     * Часовая зона г. Владивосток
     */
    private static final String ZONE_ID_VLADIVOSTOK = "Asia/Vladivostok";
    /**
     * Часовая разница между Владивостоком и Тель-Авивом
     */
    private static final String ZONE_ID_GMT = "Etc/GMT-3";

    /**
     * Отображает время полета для каждого авиаперевозчика
     *
     * @param tickets билеты, полученные из JSON
     */
    public void showFlightTimeForCarriers(TicketPOJO tickets) {
        Map<String, Ticket> map = getCarriersAndTickets(tickets);

        for (String carrier : map.keySet()) {
            System.out.print("Для авиаперевозчика " + carrier);
            System.out.println(flightTime(getFlightTime(map.get(carrier))));
        }
    }

    /**
     * Возвращает время полета в часах и минутах
     *
     * @param timeInMillis время в миллисекундах
     * @return возвращает минимальное время полета
     */
    private String flightTime(long timeInMillis) {
        long temp = TimeUnit.MILLISECONDS.toMinutes(timeInMillis);
        long hours = temp / 60;
        long minutes = temp - hours * 60;

        return String.format(" минимальноe время полета: %d часов, %d минут", hours, minutes);
    }

    /**
     * Рассчитывает время полета
     *
     * @param ticket билет, для которого необходимо рассчитать время в пути
     * @return время полета
     */
    private long getFlightTime(Ticket ticket) {
        // Получаем все необходимые данные
        String departureDate = ticket.getDeparture_date();
        String departureTime = ticket.getDeparture_time();
        String arrivalDate = ticket.getArrival_date();
        String arrivalTime = ticket.getArrival_time();

        // Время отправления из Владивостока
        ZonedDateTime startDataTime = toZonedDateTime(departureDate + " " + departureTime,
                ZONE_ID_VLADIVOSTOK);
        // Время прибытия в Тель-Авив
        ZonedDateTime finishDataTime = toZonedDateTime(arrivalDate + " " + arrivalTime, ZONE_ID_GMT);

        return ChronoUnit.MILLIS.between(startDataTime, finishDataTime);
    }

    /**
     * Определяет, какой билет позволит быстрее добраться
     * до места назначения
     *
     * @param ticket1 первый билет
     * @param ticket2 второй билет
     * @return самый быстрый авиарейс
     */
    private Ticket compareFlightTime(Ticket ticket1, Ticket ticket2) {
        if (getFlightTime(ticket1) > getFlightTime(ticket2)) {
            return ticket2;
        } else return ticket1;
    }


    /**
     * Получает всех авиаперевозчиков
     *
     * @param tickets билеты, полученные после чтения JSON
     * @return данные, которые включают в себя "ключ" и "значения".
     * Ключ - перевозчик. Значение - билет.
     */
    private Map<String, Ticket> getCarriersAndTickets(TicketPOJO tickets) {
        // Получить список билетов на авиарейс Владивосток-Тель-Авив
        List<Ticket> ticketList = findTicket(tickets);

        Map<String, Ticket> ticketMap = new HashMap<>();

        // Заполнить map (карту)
        for (Ticket ticket : ticketList) {
            if (!ticketMap.containsKey(ticket.getCarrier())) {
                ticketMap.put(ticket.getCarrier(), ticket);
            } else {
                // Определяем, какой билет позволить добраться быстрее
                Ticket fasterOne = compareFlightTime(ticketMap.get(ticket.getCarrier()), ticket);
                ticketMap.put(ticket.getCarrier(), fasterOne);
            }
        }

        return ticketMap;
    }

    /**
     * Находит билеты
     *
     * @param tickets список билетов, полученных после чтения JSON
     * @return билеты на авиарейс Владивосток-Тель-Авив
     */
    private List<Ticket> findTicket(TicketPOJO tickets) {

        return tickets.getTicketList().stream()
                .filter(ticket -> ticket.getOrigin_name().equals("Владивосток"))
                .filter(ticket -> ticket.getDestination_name().equals("Тель-Авив"))
                .collect(Collectors.toList());
    }


    /**
     * Преобразует дату к определенному формату
     *
     * @param dateTime дата, которую нужно преобразовать
     * @param zoneId   часовая зона, к которой будет приведена дата
     * @return время
     */
    private ZonedDateTime toZonedDateTime(String dateTime, String zoneId) {
        return LocalDateTime.parse(dateTime, FORMAT).atZone(ZoneId.of(zoneId));
    }

}
