import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BurgerStoreModel {
    //Nothing here
}

abstract class Burger { // An abstract class, Burger class should not be
                        // instantiated
    private static int nextID = 1;
    private ObservableList<Sauce> sauces;
    private ObservableList<Meat> meats;
    private ObservableList<Vegetable> vegetables;
    private SimpleIntegerProperty burgerID;
    private final SimpleStringProperty name;
    protected SimpleDoubleProperty basePrice;

    public Burger(String name, List<Sauce> sauces, List<Meat> meats, List<Vegetable> vegetables) {
        this.name = new SimpleStringProperty(name);
        this.sauces = FXCollections.observableArrayList(sauces);
        this.meats = FXCollections.observableArrayList(meats);
        this.vegetables = FXCollections.observableArrayList(vegetables);
        this.burgerID = new SimpleIntegerProperty(nextID);
        nextID++;
    }

    public ObservableList<Sauce> saucesProperty() {
        return sauces;
    }

    public ObservableList<Meat> meatsProperty() {
        return meats;
    }

    public ObservableList<Vegetable> vegetablesProperty() {
        return vegetables;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleIntegerProperty burgerIDProperty() {
        return burgerID;
    }

    public void setSauces(List<Sauce> sauces) {
        this.sauces = FXCollections.observableArrayList(sauces);
    }

    public void setMeats(List<Meat> meats) {
        this.meats = FXCollections.observableArrayList(meats);
    }

    public void setVegetables(List<Vegetable> vegetables) {
        this.vegetables = FXCollections.observableArrayList(vegetables);
    }

    public void setBurgerID(int burgerID) {
        this.burgerID.set(burgerID);
    }

    public List<Sauce> getSauces() {
        return sauces;
    }

    public List<Meat> getMeats() {
        return meats;
    }

    public List<Vegetable> getVegetables() {
        return vegetables;
    }

    public int getBurgerID() {
        return burgerID.get();
    }

    public String getName() {
        return name.get();
    }

    public double getBasePrice() {
        return basePrice.get();
    }

    public double countPrice() { // count the price of the burger
        double price = getBasePrice();
        for (Sauce s : sauces) {
            price += s.getPrice();
        }
        for (Meat m : meats) {
            price += m.getPrice();
        }
        for (Vegetable v : vegetables) {
            price += v.getPrice();
        }
        return price;
    }

    @Override
    public String toString() {
        String sauce = "Sauces: ";
        String meat = "Meats: ";
        String vegetable = "Vegetables: ";
        if (sauces.size() != 0) {
            for (int i = 0; i < sauces.size() - 1; i++) {
                sauce += sauces.get(i).getName() + ", ";
            }
            sauce += sauces.get(sauces.size() - 1).getName();
        } else {
            sauce += "None";
        }
        if (meats.size() != 0) {
            for (int i = 0; i < meats.size() - 1; i++) {
                meat += meats.get(i).getName() + ", ";
            }
            meat += meats.get(meats.size() - 1).getName();
        } else {
            meat += "None";
        }
        if (vegetables.size() != 0) {
            for (int i = 0; i < vegetables.size() - 1; i++) {
                vegetable += vegetables.get(i).getName() + ", ";
            }
            vegetable += vegetables.get(vegetables.size() - 1).getName();
        } else {
            vegetable += "None";
        }
        return this.getName() + "\n" + sauce + "\n" + meat + "\n" + vegetable;
    }
}

class WhiteBunBurger extends Burger {

    public WhiteBunBurger(String name, List<Sauce> sauces, List<Meat> meats, List<Vegetable> vegetables) {
        super(name, sauces, meats, vegetables);
        basePrice.set(0.5);
    }

    @Override
    public String toString() { // override the method and added bun type
        return super.toString() + "\nBuns: White Bun";
    }
}

class GlutenFreeBurger extends Burger {

    public GlutenFreeBurger(String name, List<Sauce> sauces, List<Meat> meats, List<Vegetable> vegetables) {
        super(name, sauces, meats, vegetables);
        basePrice.set(2.0);
    }

    @Override
    public String toString() { // override the method and added bun type
        return super.toString() + "\nBuns: Gulten Free Bun";
    }
}

class SeasameBurger extends Burger {

    public SeasameBurger(String name, List<Sauce> sauces, List<Meat> meats, List<Vegetable> vegetables) {
        super(name, sauces, meats, vegetables);
        basePrice.set(1.0);
    }

    @Override
    public String toString() { // override the method and added bun type
        return super.toString() + "\nBuns: Seasame Bun";
    }
}

class Recipe {
    private static Map<Integer, Burger> burgerMap = new HashMap<>(); // Set to static for global sharing
    private static ObservableList<Burger> burgerList; // Used map and observableList together, map for searching faster
                                                      // by using key, observableList for TableView UI

    public static void initRecipe(List<Burger> burger) { // initialized the burger map with a list
        for (Burger b : burger) {
            burgerMap.put(b.getBurgerID(), b);
        }
        burgerList = FXCollections.observableArrayList(burger);
    }

    public static ObservableList<Burger> burgersProperty() {
        return burgerList;
    }

    public static Map<Integer, Burger> getBurgerMap() {
        return burgerMap;
    }

    public static void delRecipe(int id) {
        Burger b = burgerMap.remove(id);
        burgerList.remove(b);
        reassignID();
    }

    private static void reassignID() { // After we delete some recipe, some ID will be missing, so we need to re-assign
                                       // the ID in order
        int id = 0;
        Map<Integer, Burger> newMap = new HashMap<>();
        for (Burger b : burgerMap.values()) {
            b.setBurgerID(id++);
            newMap.put(id, b);
        }
        burgerMap = newMap;
    }
}

class Combo {
    private SimpleObjectProperty<Burger> burger;
    private SimpleObjectProperty<Side> side;
    private SimpleObjectProperty<Drink> drink;

    public Combo(Burger burger, Side side, Drink drink) {
        this.burger = new SimpleObjectProperty<Burger>(burger);
        this.drink = new SimpleObjectProperty<Drink>(drink);
        this.side = new SimpleObjectProperty<Side>(side);
    }

    public double getPrice() {
        double price = 0;
        price += burger.get().countPrice();
        price += drink.get().getPrice();
        price += side.get().getPrice();
        return price;
    }

    public Burger getBurger() {
        return burger.get();
    }

    public Side getSide() {
        return side.get();
    }

    public Drink getDrink() {
        return drink.get();
    }
}

class Staff {
    private SimpleStringProperty password;
    private SimpleStringProperty phoneNum;
    private SimpleStringProperty name;
    private static int nextID = 1;
    private SimpleIntegerProperty staffID;

    public Staff(String password, String phoneNum, String name) {
        this.password = new SimpleStringProperty(password);
        this.phoneNum = new SimpleStringProperty(phoneNum);
        this.name = new SimpleStringProperty(name);
        this.staffID = new SimpleIntegerProperty(nextID);
        nextID++;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public SimpleStringProperty phoneNumProperty() {
        return phoneNum;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleIntegerProperty staffIDProperty() {
        return staffID;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setPhonNum(String phoneNum) {
        this.phoneNum.set(phoneNum);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPassword() {
        return password.get();
    }

    public String getPhoneNum() {
        return phoneNum.get();
    }

    public String getName() {
        return name.get();
    }

    public int getStaffID() {
        return staffID.get();
    }

    public boolean getManagerPermission() { // using the return type to finds out if the staff is a manager
        return false;
    }

    @Override
    public String toString() {
        if (getManagerPermission()) {
            return "Manager Name: " + getName() + ", Phone Number: " + getPhoneNum();
        }
        return "Staff Name: " + getName() + ", Phone Number: " + getPhoneNum();
    }
}

class Manager extends Staff {

    public Manager(String password, String phoneNum, String name) {
        super(password, phoneNum, name);
    }

    public boolean getManagerPermission() {
        return true;
    }
}

class StaffManagement {

    private static Map<Integer, Staff> staffMap = new HashMap<>(); // Set to static for global sharing
    private static ObservableList<Staff> staffList; // Used map and observableList together, map for searching faster
                                                    // by using key, observableList for TableView UI

    public static void initStaff(List<Staff> staffs) { // initialized the staff map with a list
        for (Staff s : staffs) {
            staffMap.put(s.getStaffID(), s);
        }
        staffList = FXCollections.observableArrayList(staffs);
    }

    public static Staff login(int id, String password) { // this method will either return a Staff or null based on
                                                         // the input is correct or not, it will be used in the login
                                                         // progress in the staff menu
        Staff s = staffMap.get(id);
        if (s != null && (s.getPassword().equals(password))) {
            return s;
        }
        return null;
    }

    public static ObservableList<Staff> staffsProperty() {
        return staffList;
    }

    public static Map<Integer, Staff> getStaffMap() {
        return staffMap;
    }

    public static void fireStaff(int id) {
        Staff s = staffMap.remove(id);
        staffList.remove(s);
    }
}

enum Sauce {
    MAYO("Mayo", 0.80),
    KETCHUP("Ketchup", 0.50),
    MUSTARD("Mustard", 0.50),
    RELISH("Relish", 0.80),
    BBQ_SAUCE("BBQ Sauce", 1.00),
    STEAK_SAUCE("Steak Sauce", 1.20),
    HOT_SAUCE("Hot Sauce", 1.00);

    private final SimpleStringProperty name;
    private SimpleDoubleProperty price;

    private Sauce(String name, double price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public String getName() {
        return name.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}

enum Meat {
    FISH("Fish", 2.5),
    CHICKEN("Chicken", 2.5),
    BEEF("Beef", 3.5),
    BACON("Bacon", 1.5);

    private final SimpleStringProperty name;
    private SimpleDoubleProperty price;

    private Meat(String name, double price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public String getName() {
        return name.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}

enum Vegetable {
    LETTUCE("Lettuce", 0.70),
    TOMATO("Tomato", 0.70),
    ONIONS("Onions", 0.80),
    GRILLED_ONIONS("Grilled Onions", 1.00),
    GRILLED_MUSHROOMS("Grilled Mushrooms", 1.50),
    JALAPENO("Jalapeno", 1.00),
    GREEN_PEPPERS("Green Peppers", 1.20),
    PICKLES("Pickles", 0.80);

    private final SimpleStringProperty name;
    private SimpleDoubleProperty price;

    private Vegetable(String name, double price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public String getName() {
        return name.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}

enum Side {
    FRIES("Fries", 4.0),
    ONION_RINGS("Onion Rings", 5.0),
    CHICKEN_NUGGETS("Chicken Nuggets", 6.0),
    CHICKEN_WINGS("Chicken Wings", 7.5),
    SALAD("Salad", 3.5);

    private final SimpleStringProperty name;
    private SimpleDoubleProperty price;

    private Side(String name, double price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public String getName() {
        return name.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}

enum Drink {
    COKE("Coke", 4.0),
    COKO_ZERO("Coke Zero", 4.0),
    FANTA("Fanta", 4.0),
    SPRITE("Sprite", 4.0),
    ORANGE_JUICE("Orange Juice", 5.0),
    MILK_SHAKE("Milk Shake", 6.0);

    private final SimpleStringProperty name;
    private SimpleDoubleProperty price;

    private Drink(String name, double price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public String getName() {
        return name.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}