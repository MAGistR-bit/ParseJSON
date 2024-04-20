package mag;

import mag.calculating.DifferencePrice;
import mag.calculating.MinFlightTime;
import mag.pojo.TicketPOJO;

/** @author mikhail
 * @version 1.0.0
 * Точка входа в программу
 */
public class Main {
    public static void main(String[] args) {
        TicketPOJO tickets = new TicketPOJO();
        DifferencePrice differencePrice = new DifferencePrice();
        MinFlightTime flightTime = new MinFlightTime();

        // Отобразить разницу между средней ценой и медианой
        differencePrice.showResult(tickets);

        // Отобразить время полета для каждого авиаперевозчика
        flightTime.showFlightTimeForCarriers(tickets);

    }
}