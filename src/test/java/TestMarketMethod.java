
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.Product;
import org.example.ShoppingCart;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class TestMarketMethod {
    private Map<String, Product> catalog;
    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        catalog = new HashMap<>();
        catalog.put("p1", new Product("p1", "Ноутбук", 1000.0, 10));
        catalog.put("p2", new Product("p2", "Мышь", 50.0, 5));
        cart = new ShoppingCart(catalog);
    }
    @Test
    void testCreateValidProduct() {
        Product p = new Product("p1", "Товар", 100.0, 10);
        assertEquals("p1", p.getId());
        assertEquals("Товар", p.getName());
        assertEquals(100.0, p.getPrice());
        assertEquals(10, p.getStock());
    }

    @Test
    void testCreateProductEmptyId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("", "Товар", 100.0, 10));
    }

    @Test
    void testCreateProductNegativePrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("p1", "Товар", -10.0, 10));
    }


}
