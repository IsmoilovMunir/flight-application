package by.munir.jdbc.dao;

import by.munir.jdbc.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {
    E save(E e);
    boolean delete(K id);
    List<E> findAll();
    Optional<E> findById(K id);
    boolean update(E e);

}
