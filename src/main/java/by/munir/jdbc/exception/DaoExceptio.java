package by.munir.jdbc.exception;

import java.sql.SQLException;

public class DaoExceptio extends RuntimeException {
    public DaoExceptio(Throwable e) {
        super(e);
    }
}
