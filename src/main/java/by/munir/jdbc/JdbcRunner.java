package by.munir.jdbc;

import by.munir.jdbc.dao.TicketDao;
import by.munir.jdbc.entity.Ticket;
import by.munir.jdbc.utils.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        var ticketDao = TicketDao.getInstance();
        Ticket ticket = new Ticket();
        ticket.setPassportNo("44994");
        ticket.setPassengerName("Pavel");
        ticket.setFlightId(4L);
        ticket.setSeatNo("6B");
        ticket.setCast(BigDecimal.TEN);
        System.out.println(ticketDao.save(ticket));
        System.out.println(ticketDao.delete(9L));


    }

}
