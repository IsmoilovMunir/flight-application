package by.munir.jdbc;
import by.munir.jdbc.dao.FlightDao;
import by.munir.jdbc.dao.TicketDao;
import by.munir.jdbc.entity.Flight;
import by.munir.jdbc.entity.FlightStatus;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        String dateTimeString = "2025-10-27T10:30:00";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
     var flightDao = FlightDao.getInstance();
        var ticketDao = TicketDao.getInstance();
        System.out.println(ticketDao.findById(3L).get());
        System.out.println(flightDao.findById(2L));

    }

}
