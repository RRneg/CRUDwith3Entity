package main.java.com.minaev.crud.repository;

import main.java.com.minaev.crud.model.Label;

import java.util.List;

public interface LabelRepository extends GenericRepository<Label,Integer>{

    Integer getNewId();

    Label getById(Integer id);

    Label update(Label label);

    void deleteById(Integer id);

    List<Label> getAll();
}
