
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

    @Test
    void testCreateProductNegativeStock() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("p1", "Товар", 100.0, -1));
    }

    @Test
    void testAddItemSuccess() {
        cart.addItem("p1", 3);
        assertEquals(3000.0, cart.calculateTotal());
    }

    @Test
    void testAddItemIncreaseQuantity() {
        cart.addItem("p1", 3);
        cart.addItem("p1", 2);
        assertEquals(5000.0, cart.calculateTotal());
    }

    @Test
    void testAddItemZeroQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("p1", 0));
    }

    @Test
    void testAddItemNegativeQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("p1", -5));
    }

    @Test
    void testAddItemNotInCatalog() {
        assertThrows(IllegalStateException.class,
                () -> cart.addItem("unknown", 1));
    }

    @Test
    void testAddItemExceedsStock() {
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("p1", 11));
    }

    @Test
    void testAddItemPartialExceedsStock() {
        cart.addItem("p1", 8);
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("p1", 3));
    }


}
