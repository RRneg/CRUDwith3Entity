package main.java.com.minaev.crud.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    ID getNewId();

    T getById(ID id);

    T update(T t);

    void deleteById(ID id);

    List<T> getAll();
}
