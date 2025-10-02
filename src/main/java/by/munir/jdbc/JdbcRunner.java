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
//     Flight flight = new Flight();
//        System.out.println(flight);
//        flight.setFlightNo("MN304");
//        flight.setDepartureDate(localDateTime);
//        flight.setDepartureAirportCode("MNK");
//        flight.setArrivalDate(localDateTime);
//        flight.setArrivalAirportCode("MSK");
//        flight.setAircraftId(3);
//        flight.setStatus(FlightStatus.departed);
//        System.out.println(flight);
//        System.out.println(flightDao.findAll());
//        System.out.println(flightDao.delete(1L));

        System.out.println(flightDao.findById(5l).get());

    }

}
