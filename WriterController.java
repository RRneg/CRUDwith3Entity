package main.java.com.minaev.crud.controller;

import main.java.com.minaev.crud.POJO.Post;
import main.java.com.minaev.crud.POJO.Writer;
import main.java.com.minaev.crud.repository.JavaIOWriterRepositoryImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WriterController {
    private final JavaIOWriterRepositoryImpl javaIOWriterRepositoryImpl = new JavaIOWriterRepositoryImpl();
    private Writer writer = new Writer();
    private final PostController postController = new PostController();

    public Writer newWriter(String firstName, String lastName, String ids) {
        writer.setId(javaIOWriterRepositoryImpl.getNewId());
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writer.setPosts(getListPostById(ids));
        javaIOWriterRepositoryImpl.saveWriter(writer);
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
        writer = javaIOWriterRepositoryImpl.getById(id);
        writer.setFirstName(firstName);
        javaIOWriterRepositoryImpl.update(writer);
        return writer;
    }

    public Writer changeLastName(int id, String lastName) {
        writer = javaIOWriterRepositoryImpl.getById(id);
        writer.setLastName(lastName);
        javaIOWriterRepositoryImpl.update(writer);
        return writer;
    }

    public Writer changePostId(int id, String ids) {
        writer = javaIOWriterRepositoryImpl.getById(id);
        writer.setPosts(getListPostById(ids));
        javaIOWriterRepositoryImpl.update(writer);
        return writer;
    }

    public void delWriter(int id) {
        javaIOWriterRepositoryImpl.deleteById(id);
    }

    public List<Writer> getAll() {
        return javaIOWriterRepositoryImpl.getAll();
    }

    public Writer getById(int id) {
        return javaIOWriterRepositoryImpl.getById(id);
    }

}
