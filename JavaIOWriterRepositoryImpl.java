package main.java.com.minaev.crud.repository;

import main.java.com.minaev.crud.POJO.Post;
import main.java.com.minaev.crud.POJO.Writer;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOWriterRepositoryImpl implements WriterRepository{
    private final Path path = Paths.get("src/main/resources/files/Writer.txt");
    private final JavaIOPostRepositoryImpl javaIOPostRepositoryImpl = new JavaIOPostRepositoryImpl();


    public void createNewFileNIO() {
        try {
            if (!Files.exists(path))
                Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл-репозиторий Post:" + e);
        }
    }

    private String writerToString(Writer writer) {
        return writer.getId() + ";" + writer.getFirstName() + ";"
                + writer.getLastName() + ";" + listPostsIdToString(writer.getPosts());
    }

    private String listPostsIdToString(List<Post> posts) {
         String ids = posts.stream().map(Post::getId).
                map(id -> id + ",").
                reduce((strResult, str) ->
                strResult + str).orElse(null);

            return "/" + ids.substring(0, ids.length() - 1) + "/";
    }

    private Writer stringToWriter(String str) {
        Writer writer = new Writer();
        String[] partsWriter = str.split(";");
        try {
            writer.setId(Integer.parseInt(partsWriter[0]));
        } catch (NumberFormatException e) {
            System.out.println("Не удалось преобразовать строку в post - id :" + e);
        }
        writer.setFirstName(partsWriter[1]);
        writer.setLastName(partsWriter[2]);
        writer.setPosts(getListPostById(partsWriter[3]));
        return writer;
    }

    private List<Post> getListPostById(String postids) {
        String postids1 = postids.replaceAll("/", "");
        String[] ids = postids1.split(",");
        try {
            return Arrays.stream(ids).map(Integer::parseInt).
                    map(javaIOPostRepositoryImpl::getById).
                    collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Не можем преобразовать строку в число в методе getListPostById(WriterRepository)  " + e);
            return Collections.emptyList();
        }
    }

    private List<Writer> getAllInternal() {
        try {
            return Files.readAllLines(path).stream().filter(str -> str != "").
                    map(this::stringToWriter).collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл" + e);
            return Collections.emptyList();
        }

    }

    public List<Writer> getAll() {
        return getAllInternal();
    }

    public Integer getNewId() {
        return getAllInternal().stream().max(Comparator.comparingInt(Writer::getId)).
                orElse(new Writer()).getId() + 1;
    }

    public Writer saveWriter(Writer writer) {
        String writer1 = writerToString(writer) + System.getProperty("line.separator");
        try {
            Files.writeString(path, writer1, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Не удалось записать в файл: " + e);
        }
        return writer;
    }

    public Writer getById(Integer id) {
        return getAllInternal().stream().filter(writer -> writer.getId() == id).findFirst().orElse(null);
    }

    public void deleteById(Integer id) {
        List<Writer> writers = getAllInternal();
        writers.removeIf(writer -> writer.getId() == id);
        reSaveListWriter(writers);
    }

    private void reSaveListWriter(List<Writer> writers) {
        try {
            Files.delete(path);
            createNewFileNIO();
        } catch (IOException e) {
            System.out.println("Не удалось обновить файл :" + e);
        }
        writers.stream().distinct().sorted(Comparator.comparingInt(Writer::getId)).
                collect(Collectors.toList()).
                forEach(this::saveWriter);
    }

    public Writer update(Writer writer) {
        reSaveListWriter(getAllInternal().stream().peek(writer1 -> {
            if (writer1.getId() == writer.getId()) {
                writer1.setFirstName(writer.getFirstName());
                writer1.setLastName(writer.getLastName());
                writer1.setPosts(writer.getPosts());
            }
        }).collect(Collectors.toList()));
        return writer;
    }

}
