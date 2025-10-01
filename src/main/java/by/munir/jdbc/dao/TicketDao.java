package by.munir.jdbc.dao;

import by.munir.jdbc.entity.Ticket;
import by.munir.jdbc.exception.DaoExceptio;
import by.munir.jdbc.utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;

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


    public static TicketDao getInstance() {
        return INSTANCE;
    }

    private TicketDao() {

    }
}
