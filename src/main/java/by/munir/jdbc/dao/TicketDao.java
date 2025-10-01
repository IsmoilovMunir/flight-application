package by.munir.jdbc.dao;

import by.munir.jdbc.entity.Ticket;
import by.munir.jdbc.exception.DaoExceptio;
import by.munir.jdbc.utils.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao {
    private final static TicketDao INSTANCE = new TicketDao();
    private final static String SAVE_SQL = """
            insert into ticket
            (passport_no, passenger_name, flight_id, seat_no, cost)
            values (?,?,?,?,?)
            """;
    private final static String DELETE_SQL = """
            delete from ticket
            where id = ?
            """;
    private static final String FIND_ALL_SQL = """
                        select id, passport_no, passenger_name, flight_id, seat_no, cost
            from ticket
            """;
    private static final String FIND_BY_ID = """
            select id, passport_no, passenger_name, flight_id, seat_no, cost 
            from ticket
            where id = ?
            """;

    public Ticket save(Ticket ticket) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, ticket.getPassportNo());
            statement.setString(2, ticket.getPassengerName());
            statement.setLong(3, ticket.getFlightId());
            statement.setString(4, ticket.getSeatNo());
            statement.setBigDecimal(5, ticket.getCast());

            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next())
                ticket.setId(keys.getLong("id"));
            return ticket;

        } catch (SQLException e) {
            throw new DaoExceptio(e);
        }
    }

    public boolean delete(Long id) {

        try (var connection = ConnectionManager.get();
             var statment = connection.prepareStatement(DELETE_SQL)) {
            statment.setLong(1, id);
            return statment.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ticket> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Ticket> tickets = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                tickets.add(
                        buildTicket(result)
                );
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Ticket> findById(Long id) {
        try {
            var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_BY_ID);

            statement.setLong(1, id);
            var result = statement.executeQuery();
            Ticket ticket = null;

            if (result.next())
                ticket = buildTicket(result);
            return Optional.ofNullable(ticket);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Ticket buildTicket(ResultSet result) throws SQLException {
        return new Ticket(result.getLong("id"),
                result.getString("passport_no"),
                result.getString("passenger_name"),
                result.getLong("flight_id"),
                result.getString("seat_no"),
                result.getBigDecimal("cost")
        );
    }


    public static TicketDao getInstance() {
        return INSTANCE;
    }


    private TicketDao() {

    }
}
