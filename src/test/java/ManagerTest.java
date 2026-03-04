import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.EmptyShoppingListException;
import service.Manager;
import service.ProductSaveException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Список покупок должен: ")
class ManagerTest {
    Manager manager;

    @Test
    @DisplayName("отдавать список товаров по запросу")
    void shouldGetShoppingList() {
        createShoppingList();

        String[] listToCheck = manager.getShoppingList();

        assertEquals(listToCheck[0], "молоко");
        assertEquals(listToCheck[1], "хлеб");
        assertEquals(listToCheck.length, 8);
    }

    @Test
    @DisplayName("предупреждать,если список товаров пуст")
    void shouldThrowsEmptyShoppingList() {
        createEmptyShoppingList();

        assertThrows(EmptyShoppingListException.class, () -> manager.getShoppingList());
    }

    @Test
    @DisplayName("добавлять товары в пустой список")
    void shouldAddProductToEmptyShoppingList() {
        createEmptyShoppingList();

        manager.addProductToShoppingList("хлеб");

        assertFalse(manager.listIsEmpty());
    }

    @Test
    @DisplayName("добавлять товары в список")
    void shouldAddProductToShoppingList() {
        createShoppingList();
        manager.addProductToShoppingList("сок");

        String[] listToCheck = manager.getShoppingList(); //плохо использовать два метода для проверки,
        // но я пока не придумала как еще можно получить список для проверки

        assertEquals(listToCheck[2], "сок");
    }

    @Test
    @DisplayName("не добавлять товары в переполненный список")
    void shouldThrowToFullShoppingList() {
        createShoppingList();
        manager.addProductToShoppingList("сок");
        manager.addProductToShoppingList("лук");
        manager.addProductToShoppingList("уксус");
        manager.addProductToShoppingList("масло");
        manager.addProductToShoppingList("пиво");
        manager.addProductToShoppingList("вино");

        assertThrows(ProductSaveException.class, () -> manager.addProductToShoppingList("виски"));
    }

    @Test
    @DisplayName("не добавлять товар в список, если он уже там есть")
    void shouldNotAddExistProductInShoppingList() {
        createShoppingList();
        manager.addProductToShoppingList("сок");

        assertThrows(ProductSaveException.class, () -> manager.addProductToShoppingList("сок"));
    }

    @Test
    @DisplayName("очищать список покупок")
    void shouldClearShoppingList() {
        createShoppingList();

        manager.clearShoppingList();

        assertTrue(manager.listIsEmpty());
    }

    @Test
    @DisplayName("печатать список покупок")
    void shouldPrintShoppingList() {
        createShoppingList();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        manager.printShoppingList();
        System.setOut(originalOut);
        String output = outputStream.toString();

        assertEquals(output, "1 молоко\r\n2 хлеб\r\n");  //могут быть проблемы с сепараторами
    }

    @Test
    @DisplayName("отдавать сообщение о пустом списке покупок")
    void shouldPrintEmptyShoppingList() {
        createEmptyShoppingList();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        manager.printShoppingList();
        System.setOut(originalOut);
        String output = outputStream.toString();

        assertEquals(output, "Список покупок пуст!\r\n");  //могут быть проблемы с сепараторами

    }

    void createShoppingList() {
        String[] shoppingList = new String[8]; //вручную ограничиваем длину
        shoppingList[0] = "молоко";
        shoppingList[1] = "хлеб";
        manager = new Manager(shoppingList);
        manager.setCounter();
        manager.setCounter();
    }

    void createEmptyShoppingList() {
        manager = new Manager();
    }
}