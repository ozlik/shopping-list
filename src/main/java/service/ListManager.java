package service;

public interface ListManager {
    String[] getShoppingList();
    void printShoppingList();
    void addProductToShoppingList(String product);
    void clearShoppingList();
    void extendList();
}
