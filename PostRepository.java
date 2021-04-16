package main.java.com.minaev.crud.repository;

import main.java.com.minaev.crud.POJO.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Integer>{

    Integer getNewId();

    Post getById(Integer id);

    Post update(Post post);

    void deleteById(Integer id);

    List<Post> getAll();
}
