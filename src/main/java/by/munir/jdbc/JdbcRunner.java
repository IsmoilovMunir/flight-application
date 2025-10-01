package by.munir.jdbc;

import by.munir.jdbc.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {

        System.out.println(getTicketsByFlightId(2L));
        System.out.println(getFlightsBetween(
                LocalDate.of(2020, 04, 01).atStartOfDay(),
                LocalDate.of(2020, 8, 01).atStartOfDay()));
        checkMetaData();
        getTicket();

    }


    public static List<Long> getTicketsByFlightId(Long flightId) {
        List<Long> tickets = new ArrayList<>();
        String sql = """
                select * from ticket
                where flight_id = ?
                """;
        try (Connection connection = ConnectionManager.get();
             var statement = connection.prepareStatement(sql)) {
            statement.setFetchSize(2);
            statement.setMaxRows(2);
            statement.setQueryTimeout(1);
            statement.setLong(1, flightId);
            var result = statement.executeQuery();
            while (result.next()) {
                tickets.add(result.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    public static List<Long> getFlightsBetween(LocalDateTime start, LocalDateTime end) {
        List<Long> flights = new ArrayList<>();
        String sql = """
                select* from flight
                where departure_date between ? and ?;
                """;


        try (Connection connection = ConnectionManager.get();
             var statment = connection.prepareStatement(sql)) {

            statment.setTimestamp(1, Timestamp.valueOf(start));
            statment.setTimestamp(2, Timestamp.valueOf(end));
            var result = statment.executeQuery();
            while (result.next()) {
                flights.add(result.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    public static void checkMetaData() {
        try (Connection connection = ConnectionManager.get()) {
            var metaData = connection.getMetaData();
            var catalogs = metaData.getCatalogs();
            while (catalogs.next())
                System.out.println(catalogs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getTicket() {
        String sql = """
                select * from ticket;
                
                """;
        try (Connection connection = ConnectionManager.get();
             var statement = connection.createStatement()) {
            var result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString("passenger_name"));
                System.out.println(result.getLong("flight_id"));
                System.out.println("cost");
                System.out.println("_________________");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
