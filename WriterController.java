package main.java.com.minaev.crud.controller;

import main.java.com.minaev.crud.model.Post;
import main.java.com.minaev.crud.model.Writer;
import main.java.com.minaev.crud.repository.WriterIORepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WriterController {
    private final WriterIORepository writerIORepository = new WriterIORepository();
    private Writer writer = new Writer();
    private final PostController postController = new PostController();

    public Writer newWriter(String firstName, String lastName, String ids) {
        writer.setId(writerIORepository.getNewId());
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writer.setPosts(getListPostById(ids));
        writerIORepository.saveWriter(writer);
        return writer;
    }

    private List<Post> getListPostById(String postids) {
        String[] ids = postids.split(",");
        try {
            return Arrays.stream(ids).map(Integer::parseInt).
                    map(postController::getById).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Не можем преобразовать строку в число в методе getListPostById(WriterController)  " + e);
            return Collections.emptyList();
        }
    }

    public Writer changeFirstName(int id, String firstName) {
        writer = writerIORepository.getById(id);
        writer.setFirstName(firstName);
        writerIORepository.update(writer);
        return writer;
    }

    public Writer changeLastName(int id, String lastName) {
        writer = writerIORepository.getById(id);
        writer.setLastName(lastName);
        writerIORepository.update(writer);
        return writer;
    }

    public Writer changePostId(int id, String ids) {
        writer = writerIORepository.getById(id);
        writer.setPosts(getListPostById(ids));
        writerIORepository.update(writer);
        return writer;
    }

    public void delWriter(int id) {
        writerIORepository.deleteById(id);
    }

    public List<Writer> getAll() {
        return writerIORepository.getAll();
    }

    public Writer getById(int id) {
        return writerIORepository.getById(id);
    }

}
