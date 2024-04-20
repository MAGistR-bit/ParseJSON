package mag.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import mag.model.Ticket;
import mag.model.Tickets;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TicketPOJO {
    /**
     * Расположение JSON файла
     */
    private final File file = new File("src/main/resources/tickets.json");
    /**
     * Объект, который позволяет преобразовать JSON в POJO
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Получить список билетов (после чтения JSON файла)
     * @return список билетов
     */
    public List<Ticket> getTicketList() {
        Tickets tickets = new Tickets();

        try {
            // Преобразовать JSON в POJO
            tickets = objectMapper.readValue(file, Tickets.class);
        } catch (IOException exception) {
            System.out.println("Преобразование JSON в POJO не выполнено.");
        }

        return tickets.getTickets();
    }
}
