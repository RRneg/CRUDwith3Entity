package main.java.com.minaev.crud.controller;

import main.java.com.minaev.crud.POJO.Label;
import main.java.com.minaev.crud.POJO.Post;
import main.java.com.minaev.crud.repository.JavaIOPostRepositoryImpl;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostController {
    private final JavaIOPostRepositoryImpl javaIOPostRepositoryImpl = new JavaIOPostRepositoryImpl();
    private Post post = new Post();
    private final LabelController labelController = new LabelController();


    public Post newPost(String content, String labelIds) {
        post.setId(javaIOPostRepositoryImpl.getNewId());
        post.setContent(content);
        post.setCreated(setCurrentTime());
        post.setUpdated(setCurrentTime());
        post.setLabels(getListLabelsByString(labelIds));
        javaIOPostRepositoryImpl.savePost(post);
        return post;
    }

    public Post changeContent(int id, String content) {
        post = javaIOPostRepositoryImpl.getById(id);
        post.setContent(content);
        post.setUpdated(setCurrentTime());
        javaIOPostRepositoryImpl.update(post);
        return post;
    }

    public Post changeTags(int id, String labelsId) {
        post = javaIOPostRepositoryImpl.getById(id);
        post.setUpdated(setCurrentTime());
        post.setLabels(getListLabelsByString(labelsId));
        javaIOPostRepositoryImpl.update(post);
        return post;
    }

    public void delPost(int id) {
        javaIOPostRepositoryImpl.deleteById(id);
    }

    public List<Post> getAllPost() {
        return javaIOPostRepositoryImpl.getAll();
    }

    public Post getById(int id) {
        return javaIOPostRepositoryImpl.getById(id);
    }

    private List<Label> getListLabelsByString(String labelIds) {
        String[] labelIdsAr = labelIds.split(",");
        try {
            return Arrays.stream(labelIdsAr).map(Integer::parseInt).
                    map(labelController::getById).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Не удалось преобразовать строку в число в методе getListLabelsByString :" + e);
            return Collections.emptyList();
        }
    }

    private long setCurrentTime() {
        Date now = new Date();
        return now.getTime();
    }


}
