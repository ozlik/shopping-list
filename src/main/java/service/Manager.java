package service;

public class Manager implements ListManager {
    private String[] shoppingList;
    public int MAX_LIST_SIZE = 8;
    private int counter = 0;

    public Manager(String[] shoppingList) {  //нет ограничения по размеру
        this.shoppingList = shoppingList;
    }

    public Manager() {
        shoppingList = new String[MAX_LIST_SIZE];
    }

    public void setCounter() {
        counter++;
    }

    @Override
    public String[] getShoppingList() {
        if (listIsEmpty()) {
            throw new EmptyShoppingListException();
        }
        return shoppingList;
    }

    @Override
    public void printShoppingList() {
        if (listIsEmpty()) {
            System.out.println("Список покупок пуст!");
        } else {
            for (int i = 0; i < counter; i++) {
                System.out.println((i + 1) + " " + shoppingList[i]);
            }
        }
    }

    @Override
    public void addProductToShoppingList(String product) {
        boolean isProductExist = false;
        if (counter == MAX_LIST_SIZE) {
            extendList();
        }
        for (int i = 0; i < counter; i++) {
            if (shoppingList[i].equals(product)) {
                isProductExist = true;
                break;
            }
        }
        if (isProductExist) {
            throw new ProductSaveException("Товар уже есть в списке");
        } else {
            shoppingList[counter] = product;
            setCounter();
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

    public boolean listIsEmpty() {
        if (counter == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void extendList() {
        String[] newShoppingList = new String[shoppingList.length + 1];
        for (int i = 0; i < shoppingList.length; i++) {  /*можно переключить на натив  System.arraycopy, но не имеет смысла
            так как массив списка покупок не планируется большим и не целесообразно переключаться на нативный код
            newShoppingList[i] = shoppingList[i]; */
        }
        shoppingList = newShoppingList;
        MAX_LIST_SIZE++;
    }
}
