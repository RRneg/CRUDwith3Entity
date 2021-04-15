package main.java.com.minaev.crud.controller;

import main.java.com.minaev.crud.model.Label;
import main.java.com.minaev.crud.repository.LabelIORepository;

import java.util.List;
import java.util.stream.Collectors;

public class LabelController {
    private final LabelIORepository labelIORepository = new LabelIORepository();

    public Label newLabel(String category) {
        return labelIORepository.saveLabel(new Label(labelIORepository.getNewId(), category));
    }


    public Label updateLabel(int id, String label) {
        return labelIORepository.update(new Label(id, label));
    }


    public void deleteLabel(int id) {
        labelIORepository.deleteById(id);
    }

    public Label getById(int id) {
        return labelIORepository.getById(id);
    }


    public List<String> getAll() {

        return labelIORepository.getAll().stream().
                map(Label::toString).
                collect(Collectors.toList());
    }

}
