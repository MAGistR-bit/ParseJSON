import mag.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {
    @Test
    void getFlightTime() throws ParseException {
        Ticket ticket = new Ticket();
        ticket.setDeparture_date("12.05.18");
        ticket.setDeparture_time("10:20");
        ticket.setArrival_date("12.05.18");
        ticket.setArrival_time("23:55");

        String departureDate1 = ticket.getDeparture_date();
        String departureTime1 = ticket.getDeparture_time();
        String arrivalDate1 = ticket.getArrival_date();
        String arrivalTime1 = ticket.getArrival_time();

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyHH:mm");
        Date departure = formatter.parse(departureDate1 + departureTime1);
        Date arrive = formatter.parse(arrivalDate1 + arrivalTime1);

        Assertions.assertEquals(48900000,arrive.getTime() - departure.getTime());
    }
}
