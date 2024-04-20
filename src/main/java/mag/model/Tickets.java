package mag.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Tickets {
    /**
     * Список билетов
     */
    private List<Ticket> tickets;
}
