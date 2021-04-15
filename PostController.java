package main.java.com.minaev.crud.controller;

import main.java.com.minaev.crud.model.Label;
import main.java.com.minaev.crud.model.Post;
import main.java.com.minaev.crud.repository.PostIORepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PostController {
    private final PostIORepository postIORepository = new PostIORepository();
    private Post post = new Post();
    private final LabelController labelController = new LabelController();


    public Post newPost(String content, String labelIds) {
        post.setId(postIORepository.getNewId());
        post.setContent(content);
        post.setCreated(setCurrentTime());
        post.setUpdated(setCurrentTime());
        post.setLabels(getListLabelsByString(labelIds));
        postIORepository.savePost(post);
        return post;
    }

    public Post changeContent(int id, String content) {
        post = postIORepository.getById(id);
        post.setContent(content);
        post.setUpdated(setCurrentTime());
        postIORepository.update(post);
        return post;
    }

    public Post changeTags(int id, String labelsId) {
        post = postIORepository.getById(id);
        post.setUpdated(setCurrentTime());
        post.setLabels(getListLabelsByString(labelsId));
        postIORepository.update(post);
        return post;
    }

    public void delPost(int id) {
        postIORepository.deleteById(id);
    }

    public List<Post> getAllPost() {
        return postIORepository.getAll();
    }

    public Post getById(int id) {
        return postIORepository.getById(id);
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
