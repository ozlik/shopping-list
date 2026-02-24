package service;

public class Manager implements ListManager {
    private String[] shoppingList;
    final int MAX_LIST_SIZE = 8;
    private int counter = 0;

    public Manager() {
        shoppingList = new String[MAX_LIST_SIZE];
    }

    private void setCounter() {
        counter++;
    }

    @Override
    public void getShoppingList() {
        if (counter != 0) {
            for (int i = 0; i < counter; i++) {
                System.out.println((i + 1) + " " + shoppingList[i]);
            }
        } else {
            System.out.println("Список покупок пуст!");
        }
    }

    @Override
    public void addProductToShoppingList(String product) {
        if (counter < MAX_LIST_SIZE) {
            shoppingList[counter] = product;
            setCounter();
            System.out.println("Товар добавлен в список");
        } else {
            throw new ProductSaveException("Нельзя добавить товар. В списке максимальное количество товаров: "
                    + MAX_LIST_SIZE);
        }
    }

    @Override
    public void clearShoppingList() {
        for (int j = 0; j <= counter; j++) {
            shoppingList[j] = null;
        }
        counter = 0;
        System.out.println("Список покупок очищен!");
    }
}
