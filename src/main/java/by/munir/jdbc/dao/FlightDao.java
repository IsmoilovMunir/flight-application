package by.munir.jdbc.dao;

import by.munir.jdbc.entity.Flight;
import by.munir.jdbc.entity.FlightStatus;
import by.munir.jdbc.exception.DaoExceptio;
import by.munir.jdbc.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private final static FlightDao INSTANCE = new FlightDao();
    private final static String SAVE_SQL = """
            insert into flight
            (flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status)
            values (?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id, flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status
            FROM flight
            """;
    public static final String DELETE_SQL = """
            delete from flight
            where id = ?
            """;

    public static final String FIND_BY_ID_SQL = """
            SELECT id, flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status
            from flight
            where id =?
            """;
    public static final String UPDATE_SQL = """
            UPADATE flight
            set flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status
            WHERE id = ?
            """;

    @Override
    public Flight save(Flight flight) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL)) {
            statement.setString(1, flight.getFlightNo());
            statement.setObject(2, flight.getDepartureDate());
            statement.setString(3, flight.getDepartureAirportCode());
            statement.setObject(4, flight.getArrivalDate());
            statement.setString(5, flight.getArrivalAirportCode());
            statement.setInt(6, flight.getAircraftId());
            statement.setObject(7, flight.getStatus());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next())
                flight.setId(keys.getLong("id"));
            return flight;
        } catch (SQLException e) {
            throw new DaoExceptio(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (var conection = ConnectionManager.get();
             var statement = conection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Flight> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Flight> flights = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                flights.add(
                        buldFlight(result)
                );
            return flights;
        } catch (SQLException e) {
            throw new DaoExceptio(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            var result = statement.executeQuery();
            Flight flight = null;
            if (result.next()) {
                flight = buldFlight(result);
            }
            return Optional.ofNullable(flight);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Flight flight) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, flight.getFlightNo());
            statement.setObject(2, flight.getDepartureDate());
            statement.setString(3, flight.getDepartureAirportCode());
            statement.setObject(4, flight.getArrivalDate());
            statement.setString(5, flight.getArrivalAirportCode());
            statement.setInt(6, flight.getAircraftId());
            statement.setObject(7, flight.getStatus());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Flight buldFlight(ResultSet result) throws SQLException {
        return new Flight(
                result.getLong("id"),
                result.getString("flight_no"),
                result.getTimestamp("departure_date").toLocalDateTime(),
                result.getString("departure_airport_code"),
                result.getTimestamp("arrival_date").toLocalDateTime(),
                result.getString("arrival_airport_code"),
                result.getInt("aircraft_id"),
                FlightStatus.valueOf(result.getString("status"))
        );
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }

    private FlightDao() {
    }
}
