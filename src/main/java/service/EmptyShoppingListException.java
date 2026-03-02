package service;

public class EmptyShoppingListException extends RuntimeException {
    public EmptyShoppingListException() {
        super("Список покупок пуст!");
    }
}
