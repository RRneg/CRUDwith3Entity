package main.java.com.minaev.crud.repository;

import main.java.com.minaev.crud.POJO.Label;

import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class JavaIOLabelRepositoryImpl implements LabelRepository{

    public JavaIOLabelRepositoryImpl() {

    }

    private final Path path = Paths.get("src/main/resources/files/Lables.txt");

    public Integer getNewId() {
        return getAllInternal().stream().
                max(Comparator.comparingInt(Label::getId)).
                orElse(new Label(0, null)).getId() + 1;
    }


    private void createNewFileNIO() {
        try {
            if (!Files.exists(path))
                Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл-репозиторий Label:" + e);
        }
    }

    public Label getById(Integer id) {
        return getAllInternal().stream().
                filter((a) -> a.getId() == id).
                findFirst().orElse(null);
    }

    private List<Label> getAllInternal() {
        try {
            return Files.readAllLines(path).stream().filter(str -> str != "").
                    map(this::stringToLabel).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл" + e);
            return Collections.emptyList();
        }
    }


    public Label update(Label label) {
        reSaveListLabel(getAllInternal().stream().peek(label1 -> {
            if (label1.getId() == label.getId()) {
                label1.setCategory(label.getCategory());
            }
        }).collect(Collectors.toList()));
        return label;
    }

    private void reSaveListLabel(List<Label> labels) {
        try {
            Files.delete(path);
            createNewFileNIO();
        } catch (IOException e) {
            System.out.println("Не удалось обновить файл :" + e);
        }
        labels.stream().distinct().
                sorted(Comparator.comparingInt(Label::getId)).
                collect(Collectors.toList()).
                forEach(this::saveLabel);
    }


    public void deleteById(Integer id) {
        List<Label> labels = getAllInternal();
        labels.removeIf(label -> label.getId() == id);
        reSaveListLabel(labels);
    }


    public List<Label> getAll() {
        return getAllInternal();
    }

    public Label saveLabel(Label label) {
        String lable1 = labelToString(label) + System.getProperty("line.separator");
        try {
            Files.writeString(path, lable1, StandardOpenOption.APPEND);


        } catch (IOException e) {
            System.out.println("Не удалось записать в файл(((: " + e);

        }
        return label;
    }

    private String labelToString(Label label) {
        return label.getId() + "," + label.getCategory();
    }

    private Label stringToLabel(String str) {
        String[] partsLabel = str.split(",");
        Label label = new Label();
        try {
            int id = Integer.parseInt(partsLabel[0]);
            label.setId(id);
        } catch (NumberFormatException e) {
            System.out.println("Не можем преобразовать строку в число в Label" + e);
        }
        String category = partsLabel[1];
        label.setCategory(category);
        return label;
    }

}


