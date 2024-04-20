package mag.calculating;

import mag.pojo.TicketPOJO;
import mag.model.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mikhail
 * @version 1.0.0
 */
public class DifferencePrice {
    /**
     * Пункт отправления
     */
    private final String originName = "Владивосток";
    /**
     * Пункт назначения
     */
    private final String destinationName = "Тель-Авив";

    /**
     * Определяет стоимость билета на рейс Владивосток-Тель-Авив
     *
     * @param tickets билеты, которые имеются в продаже
     * @return стоимость каждого билета
     */
    private List<Double> findPrices(TicketPOJO tickets) {
        List<Double> prices = new ArrayList<>(
                tickets.getTicketList().stream()
                        .filter(x -> x.getOrigin_name().equals(originName))
                        .filter(x -> x.getDestination_name().equals(destinationName))
                        .map(Ticket::getPrice)
                        .toList()
        );

        Collections.sort(prices);
        return prices;
    }

    /**
     * Вычисляет среднюю цену билета на рейс
     *
     * @param tickets билеты, имеющиеся в продаже
     * @return средняя цена билета
     */
    private double averageTicketPrice(TicketPOJO tickets) {
        List<Ticket> ticketList = tickets.getTicketList();

        return ticketList.stream()
                .filter(x -> x.getOrigin_name().equals(originName) && x.getDestination_name().equals(destinationName))
                .mapToDouble(Ticket::getPrice)
                .average().orElse(0);

    }

    /**
     * Вычисляет медиану цен
     * @param tickets билеты, которые имеются в продаже
     * @return медиана цен
     */
    private double medianPrices(TicketPOJO tickets) {
        if (findPrices(tickets).size() % 2 == 0) {
            return ((findPrices(tickets).get((findPrices(tickets).size() / 2) - 1)
                    + findPrices(tickets).get(findPrices(tickets).size() / 2)) / 2);
        } else {
            return findPrices(tickets).get(findPrices(tickets).size() / 2);
        }
    }

    /**
     * Отображает разницу между средней ценой и медианой
     * @param ticketPojo объект, который получен после преобразования из JSON
     */
    public void showResult(TicketPOJO ticketPojo) {
        double diff = calculatePriceDifference(ticketPojo);
        System.out.println("Разница между средней ценой и медианой для " +
                "полета между городами Владивосток и Тель-Авив: " + diff);
    }

    /**
     * Вычисляет разницу между средней ценой и медианой
     * @param tickets билеты
     * @return число типа double - разница цен
     */
    private double calculatePriceDifference(TicketPOJO tickets) {
        return averageTicketPrice(tickets) - medianPrices(tickets);
    }
}
