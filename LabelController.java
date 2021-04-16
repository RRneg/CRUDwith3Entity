package main.java.com.minaev.crud.controller;

import main.java.com.minaev.crud.POJO.Label;
import main.java.com.minaev.crud.repository.JavaIOLabelRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class LabelController {
    private final JavaIOLabelRepositoryImpl javaIOLabelRepositoryImpl = new JavaIOLabelRepositoryImpl();

    public Label newLabel(String category) {
        return javaIOLabelRepositoryImpl.saveLabel(new Label(javaIOLabelRepositoryImpl.getNewId(), category));
    }


    public Label updateLabel(int id, String label) {
        return javaIOLabelRepositoryImpl.update(new Label(id, label));
    }


    public void deleteLabel(int id) {
        javaIOLabelRepositoryImpl.deleteById(id);
    }

    public Label getById(int id) {
        return javaIOLabelRepositoryImpl.getById(id);
    }


    public List<String> getAll() {

        return javaIOLabelRepositoryImpl.getAll().stream().
                map(Label::toString).
                collect(Collectors.toList());
    }

}
