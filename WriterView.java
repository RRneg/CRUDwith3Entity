package main.java.com.minaev.crud.view;

import main.java.com.minaev.crud.controller.WriterController;
import main.java.com.minaev.crud.model.Writer;

import java.util.Scanner;

public class WriterView {
    private final String menu = "Выберите тип операции, введя соответсвующий номер";
    private final String menu1 = "Для добавления записи нажмите 1";
    private final String menu2 = "Для изменения существующей записи нажмите 2";
    private final String menu3 = "Для удаления существующей записи нажмите 3";
    private final String menu4 = "Для получения списка записей нажмите 4";
    private final String menu5 = "Для получения записи по id нажмите 5";
    private final String menu6 = "Для выхода в главное меню нажмите 6";
    private final String menu21 = "Для изменения First Name нажмите 1";
    private final String menu211 = "Введите First Name";
    private final String menu22 = "Для изменения Last Name нажмите 2";
    private final String menu221 = "Введите Last Name";
    private final String menu23 = "Для изменения списка Post нажмите 3";
    private final String menu231 = "Введите список id записей Post через запятую";
    private final String menu3151 = "Введите id записи";
    private final String menu32 = "Запись успешно удалена";
    private final String menu61 = "Вы успешно вышли";
    private final Scanner scanner = new Scanner(System.in);
    private final PostView postView = new PostView();
    private final WriterController writerController = new WriterController();


    private void startMenu() {
        System.out.println(menu);
        System.out.println(menu1);
        System.out.println(menu2);
        System.out.println(menu3);
        System.out.println(menu4);
        System.out.println(menu5);
        System.out.println(menu6);
    }

    public void choiceMenuWriter() {
        int choice;
        do {
            startMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    newWriter();
                    break;
                case 2:
                    changeWriter();
                    break;
                case 3:
                    System.out.println(menu3151);
                    delWriter(scanner.nextInt());
                    break;
                case 4:
                    getAll();
                    break;
                case 5:
                    System.out.println(menu3151);
                    getById(scanner.nextInt());
                    break;
                case 6:
                    System.out.println(menu61);
                    break;
            }
        }
        while (choice != 6);
    }

    private void newWriter() {
        System.out.println(menu211);
        String firstName = scanner.next();
        System.out.println(menu221);
        String lastName = scanner.next();
        System.out.println(menu231);
        postView.viewGetAllPosts();
        String ids = scanner.next();
        printWriter(writerController.newWriter(firstName, lastName, ids));
    }

    private void changeWriter() {
        System.out.println(menu3151);
        int id = scanner.nextInt();
        System.out.println(menu21);
        System.out.println(menu22);
        System.out.println(menu23);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println(menu211);
                changeFistNameWriter(id, scanner.next());
                break;
            case 2:
                System.out.println(menu221);
                changeLastWriter(id, scanner.next());
                break;
            case 3:
                System.out.println(menu231);
                postView.viewGetAllPosts();
                changePostId(id, scanner.next());
                break;

        }
    }

    private void changeFistNameWriter(int id, String fistName) {
        printWriter(writerController.changeFirstName(id, fistName));
    }

    private void changeLastWriter(int id, String lastName) {
        printWriter(writerController.changeLastName(id, lastName));
    }

    private void changePostId(int id, String postIds) {
        printWriter(writerController.changePostId(id, postIds));
    }

    private void delWriter(int id) {
        writerController.delWriter(id);
        System.out.println(menu32);
    }

    private void getAll() {
        writerController.getAll().forEach(this::printWriter);
    }

    private void getById(int id) {
        printWriter(writerController.getById(id));
    }

    private void printWriter(Writer writer) {
        System.out.println("Id = " + writer.getId() + " First Name : " + writer.getFirstName()
                + " Last Name : " + writer.getLastName());
        System.out.println("          Post(s):");
        writer.getPosts()
                .forEach(postView::printPost);
        System.out.println();
    }

}
