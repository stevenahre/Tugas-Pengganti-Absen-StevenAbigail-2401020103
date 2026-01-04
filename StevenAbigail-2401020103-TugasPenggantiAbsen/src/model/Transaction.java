package model;

public class Transaction {
    private String id;
    private String name;
    private int quantity;
    private int price;

    public Transaction(String id, String name, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public int getPrice() { return price; }

    public int getTotal() {
        return quantity * price;
    }
}
