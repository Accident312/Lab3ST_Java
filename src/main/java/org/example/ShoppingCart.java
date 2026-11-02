package org.example;
import org.example.Product;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private  Map<String, Product> catalog;
    private  Map<String, Integer> items;
    private String appliedPromoCode;
    private  Map<String, Double> promoCodes;

    public ShoppingCart(Map<String, Product> catalog) {
        this.catalog = catalog;
        this.items = new HashMap<>();
        this.appliedPromoCode = null;
        this.promoCodes = new HashMap<>();
        promoCodes.put("DISCOUNT10", 0.10);
        promoCodes.put("SALE20", 0.20);
        promoCodes.put("VIP50", 0.50);
    }
    public void addItem(String productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше нуля");
        }
        if (!catalog.containsKey(productId)) {
            throw new IllegalStateException("Товар '" + productId + "' не найден в каталоге");
        }

        Product product = catalog.get(productId);
        int current = items.getOrDefault(productId, 0);

        if (current + quantity > product.getStock()) {
            throw new IllegalArgumentException(
                    "Недостаточно '" + product.getName() + "' на складе. " +
                            "Доступно: " + (product.getStock() - current) + ", запрошено: " + quantity
            );
        }

        items.put(productId, current + quantity);
    }

    public void removeItem(String productId, Integer quantity) {
        if (!items.containsKey(productId)) {
            throw new IllegalArgumentException();
        }

        if (quantity == null) {
            items.remove(productId);
        } else {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Количество для удаления должно быть > 0");
            }
            int newQty = items.get(productId) - quantity;
            if (newQty <= 0) {
                items.remove(productId);
            } else {
                items.put(productId, newQty);
            }
        }
    }
    public void applyPromoCode(String code) {
        if (!promoCodes.containsKey(code)) {
            throw new IllegalStateException("Промокод '" + code + "' недействителен");
        }
        this.appliedPromoCode = code;
    }
    public void clearPromoCode() {
        this.appliedPromoCode = null;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            Product product = catalog.get(entry.getKey());
            total += product.getPrice() * entry.getValue();
        }

        if (appliedPromoCode != null) {
            double discount = promoCodes.get(appliedPromoCode);
            total *= (1 - discount);
        }

        return Math.round(total * 100.0) / 100.0;
    }

}
