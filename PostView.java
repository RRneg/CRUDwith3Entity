package main.java.com.minaev.crud.view;

import main.java.com.minaev.crud.controller.PostController;
import main.java.com.minaev.crud.model.Post;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PostView {
    private final String menu = "Выберите тип операции, введя соответсвующий номер";
    private final String menu1 = "Для добавления записи нажмите 1";
    private final String menu11 = "Добавьте контент";
    private final String menu12 = "Добавьте id тегов из списка ниже через запятую, после добавления всех тегов нажмите Enter";
    private final String menu2 = "Для изменения записи нажмите 2";
    private final String menu21 = "Для изменения контента нажмите 1";
    private final String menu22 = "Для изменения тегов нажмите 2";
    private final String menu23 = "Введите новый контент";
    private final String menu25 = "Введите новые id тегов через запятую";
    private final String menu26 = "Введите id записи, которую хотите изменить";
    private final String menu3 = "Для удаления записи нажмите 3";
    private final String menu31 = "Введите id записи, которую хотите удалить";
    private final String menu32 = "Запись успешно удалена";
    private final String menu4 = "Для просмотра всех записей нажмите 4";
    private final String menu5 = "Для получения записи по id, нажмите 5";
    private final String menu51 = "Введите id";
    private final String menu6 = "Для выхода в главное меню нажмите 6";
    private final String menu61 = "Вы успешно вышли";
    private final Scanner scanner = new Scanner(System.in);
    private final LabelView labelView = new LabelView();
    private final PostController postController = new PostController();
    private final String dateFormat = "dd.MM.yyyy HH:mm:ss:SSS";


    private void postView() {
        System.out.println(menu);
        System.out.println(menu1);
        System.out.println(menu2);
        System.out.println(menu3);
        System.out.println(menu4);
        System.out.println(menu5);
        System.out.println(menu6);
    }


    public void choiceMenuPost() {
        int choice;
        do {
            postView();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAddMenuPost();
                    break;
                case 2:
                    viewChangeMenuPost();
                    break;
                case 3:
                    viewDelPost();
                    break;
                case 4:
                    viewGetAllPosts();
                    break;
                case 5:
                    viewGetById();
                    break;
                case 6:
                    System.out.println(menu61);
            }

        }
        while (choice != 6);

    }


    private void viewAddMenuPost() {
        System.out.println(menu11);
        String content = scanner.next();
        System.out.println(menu12);
        labelView.viewAllLabel();
        String tags = scanner.next();
        printPost(postController.newPost(content, tags));
    }

    private void viewChangeMenuPost() {
        System.out.println(menu26);
        viewGetAllPosts();
        int id = scanner.nextInt();
        System.out.println(menu21);
        System.out.println(menu22);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println(menu23);
                String content = scanner.next();
                viewChangeContent(id, content);
                break;
            case 2:
                System.out.println(menu25);
                labelView.viewAllLabel();
                viewChanceTags(id, scanner.next());
                break;

        }
    }

    private void viewChanceTags(int id, String tags) {
        printPost(postController.changeTags(id, tags));
    }

    private void viewChangeContent(int id, String content) {
        printPost(postController.changeContent(id, content));
    }

    private void viewDelPost() {
        System.out.println(menu31);
        postController.delPost(scanner.nextInt());
        System.out.println(menu32);
    }

    public void viewGetAllPosts() {
        postController.getAllPost().
                forEach(this::printPost);
    }


    private String longTimeToString(long time) {
        Date date = new Date(time);
        return new SimpleDateFormat(dateFormat).format(date);
    }

    public void printPost(Post post) {
        System.out.println("id = " + post.getId() + "  content: "
                + post.getContent() + " post created at: "
                + longTimeToString(post.getCreated()) +
                "  post updated at: " + longTimeToString(post.getUpdated()) +
                " post tags: " + post.getLabels());
    }


    private void viewGetById() {
        System.out.println(menu51);
        printPost(postController.getById(scanner.nextInt()));
    }

}
