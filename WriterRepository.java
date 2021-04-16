package main.java.com.minaev.crud.repository;

import main.java.com.minaev.crud.POJO.Writer;

import java.util.List;

public interface WriterRepository extends GenericRepository<Writer, Integer>{

    Integer getNewId();

    Writer getById(Integer id);

    Writer update(Writer writer);

    void deleteById(Integer id);

    List<Writer> getAll();
}
