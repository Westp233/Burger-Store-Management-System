

import java.util.ArrayList;
import java.util.List;

// Import enums from Burger.java (copy the enums Sauce, Meat, Vegetable, etc. here or import them if in the same package)

public class BurgerStoreModel {
    private List<Burger> menu;
    private List<Order> orders;

    public BurgerStoreModel() {
        menu = new ArrayList<>();
        orders = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        // Example burgers using enums from Burger.java
        menu.add(new WhiteBunBurger(
            "Classic Burger",
            List.of(Sauce.MAYO, Sauce.KETCHUP),
            List.of(Meat.BEEF),
            List.of(Vegetable.LETTUCE, Vegetable.TOMATO)
        ));
        menu.add(new SeasameBurger(
            "Chicken Burger",
            List.of(Sauce.MAYO),
            List.of(Meat.CHICKEN),
            List.of(Vegetable.LETTUCE, Vegetable.ONIONS)
        ));
        menu.add(new GlutenFreeBurger(
            "Veggie Burger",
            List.of(Sauce.RELISH),
            List.of(),
            List.of(Vegetable.LETTUCE, Vegetable.TOMATO, Vegetable.GRILLED_MUSHROOMS)
        ));
    }

    public List<Burger> getMenu() {
        return menu;
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }
}

// Example Order class for JavaFX model
class Order {
    private List<Burger> burgers = new ArrayList<>();
    private double total = 0.0;

    public void addBurger(Burger burger) {
        burgers.add(burger);
        total += burger.countPrice();
    }

    public List<Burger> getBurgers() {
        return burgers;
    }

    public double getTotal() {
        return total;
    }
}