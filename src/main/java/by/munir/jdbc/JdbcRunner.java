package by.munir.jdbc;
import by.munir.jdbc.dao.TicketDao;
import by.munir.jdbc.dto.TicketFilter;
import by.munir.jdbc.entity.Ticket;

import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
     var ticketDao = TicketDao.getInstance();
     var filter = new TicketFilter(null, null, 10, 0);
        System.out.println(ticketDao.findAll(filter));

    }

}
