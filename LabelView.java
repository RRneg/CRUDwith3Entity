package main.java.com.minaev.crud.view;

import main.java.com.minaev.crud.controller.LabelController;


import java.util.Scanner;

public class LabelView {
    private final Scanner scanner = new Scanner(System.in);
    private final String menu = "Выберите тип операции, введя соответсвующий номер";
    private final String menu1 = "Для добавления записи нажмите 1";
    private final String menu2 = "Для изменения категории в существующей записи нажмите 2";
    private final String menu3 = "Для удаления существующей категории нажмите 3";
    private final String menu4 = "Для получения списка категорий нажмите 4";
    private final String menu5 = "Для получения категории по id нажмите 5";
    private final String menu6 = "Для выхода в главное меню нажмите 6";
    private final String choiceMenu1 = "Введите категорию";
    private final String choiceMenu2 = "Введите id категории";
    private final String choiceMenu3 = "Введите новую категорию";
    private final String choiceMenu4 = "Введите id категории, котрую вы хотите удалить";
    private final String choiceMenu5 = "Введите id категории, котрую вы хотите найти";
    private final String choiceMenu6 = "Вы успешно вышли";
    private final LabelController labelController = new LabelController();


    private void startMenu() {
        System.out.println(menu);
        System.out.println(menu1);
        System.out.println(menu2);
        System.out.println(menu3);
        System.out.println(menu4);
        System.out.println(menu5);
        System.out.println(menu6);
    }


    public void choiceMenuLabel() {
        int choice;
        do {
            startMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(choiceMenu1);
                    viewNewLabel(scanner.next());
                    break;
                case 2:
                    viewAllLabel();
                    System.out.println(choiceMenu2);
                    int id = scanner.nextInt();
                    System.out.println(choiceMenu3);
                    viewUpdateLabel(id, scanner.next());
                    break;
                case 3:
                    System.out.println(choiceMenu4);
                    viewAllLabel();
                    viewDeleteLabel(scanner.nextInt());
                    break;
                case 4:
                    viewAllLabel();
                    break;
                case 5:
                    System.out.println(choiceMenu5);
                    viewGetById(scanner.nextInt());
                    break;
                case 6:
                    System.out.println(choiceMenu6);
                    break;
            }
        } while (choice != 6);
    }


    private void viewNewLabel(String category) {
        System.out.println(labelController.newLabel(category).toString());
    }

    private void viewUpdateLabel(int id, String category) {
        System.out.println(labelController.updateLabel(id, category).toString());
    }

    private void viewDeleteLabel(int id) {
        labelController.deleteLabel(id);
        System.out.println("Запись успешно удалена");
    }

    private void viewGetById(int id) {
        System.out.println(labelController.getById(id).toString());
    }

    public void viewAllLabel() {
        labelController.getAll().forEach(System.out::println);
    }

}
