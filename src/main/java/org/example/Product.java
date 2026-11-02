package org.example;

public class Product {
    private String product_id;
    private String name;
    private double price;
    private int stock;

    public Product(String id, String name, double price, int stock) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID товара не может быть пустым");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Количество на складе не может быть отрицательным");
        }

        this.product_id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() { return product_id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}
