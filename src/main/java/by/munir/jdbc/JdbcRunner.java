package by.munir.jdbc;
import by.munir.jdbc.dao.TicketDao;
import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        var ticketDao = TicketDao.getInstance();
        System.out.println(ticketDao.findAll());
        System.out.println(ticketDao.findById(5L).get());

    }

}
