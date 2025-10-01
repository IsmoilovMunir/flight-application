package by.munir.jdbc;
import by.munir.jdbc.dao.TicketDao;
import by.munir.jdbc.entity.Ticket;

import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        var ticketDao = TicketDao.getInstance();
       Ticket ticket = ticketDao.findById(5L).get();

        System.out.println(ticket);
        ticket.setSeatNo("40D");
        ticket.setPassengerName("Сергей Миронов");
        System.out.println(ticketDao.update(ticket));
        System.out.println(ticket);

    }

}
