import service.Manager;
import service.ProductSaveException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;

        while (isActive) {
            int command;

            System.out.println("Выберите одну из команд:");
            System.out.println("1. Добавить товар в список");
            System.out.println("2. Показать список");
            System.out.println("3. Очистить список");
            System.out.println("4. Завершить работу");

            try {
                command = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число!");
                scanner.next();
                continue;
            }

            switch (command) {
                case 1 -> {
                    System.out.println("Введите название товара без пробелов. Например: сгущеное_молоко");
                    String product = scanner.next();
                    try {
                        manager.addProductToShoppingList(product);
                        System.out.println("Товар добавлен в список");
                    } catch (ProductSaveException e) {
                        System.err.println("Ошибка при добавлении товара: " + e.getMessage());
                    }
                }
                case 2 -> manager.printShoppingList();
                case 3 -> manager.clearShoppingList();
                case 4 -> isActive = false;
                default -> System.out.println("Команда не найдена. Попробуйте ещё раз.");
            }
        }
        scanner.close();
    }
}
