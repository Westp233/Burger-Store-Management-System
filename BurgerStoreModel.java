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
    private ObservableList<Burger> burgers;
    private ObservableList<Staff> staffs;

    BurgerStoreModel() {
        burgers = FXCollections.observableArrayList();
        staffs = FXCollections.observableArrayList();
    }

    public ObservableList<Burger> burgersProperty() {
        return this.burgers;
    }

    public ObservableList<Staff> staffsProperty() {
        return this.staffs;
    }

    public void initRecipe(List<Burger> burger) { // initialized the burgers with a list
        this.burgers = FXCollections.observableArrayList(burger);
    }

    public void addRecipe(Burger b) {
        burgers.add(b);
    }

    public void removeRecipe(int index) {
        burgers.remove(index);
        reassignID();
    }

    private void reassignID() { //needs to reassign the id after deleting a burger to make sure everthing is in order
        int id = 0;
        for (Burger b : burgers) {
            b.setBurgerID(++id);
        }
    }

    public void initStaff(List<Staff> staff) { // initialized the staffs with a list
        this.staffs = FXCollections.observableArrayList(staff);
    }

    public Staff login(int id, String password) {
        for (Staff s : staffs) {
            if ((s.getStaffID() == id) && (s.getPassword().equals(password))) {
                return s;
            }
        }
        return null;
    }

    public void addStaff(Staff s) {
        staffs.add(s);
    }

    public void updateStaff(Staff s, int index) {
        staffs.set(index, s);
    }

    public void removeStaff(int index) { //staff don't need to reassign the id cuz they need to use it for login
        staffs.remove(index);
    }
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
        this.basePrice = new SimpleDoubleProperty(0.0);
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

    public SimpleStringProperty ingredientProperty() {
        SimpleStringProperty ingredient = new SimpleStringProperty(this.toString());
        return ingredient;
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
    protected SimpleStringProperty permission;

    public Staff(String password, String phoneNum, String name) {
        this.password = new SimpleStringProperty(password);
        this.phoneNum = new SimpleStringProperty(phoneNum);
        this.name = new SimpleStringProperty(name);
        this.staffID = new SimpleIntegerProperty(nextID);
        this.permission = new SimpleStringProperty("Staff");
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

    public SimpleStringProperty permissionProperty() {
        return permission;
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

    public void setPermission(String Permission) {
        this.permission.set(Permission);
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

    public String getPermission() {
        return permission.get();
    }
}

class Manager extends Staff {

    public Manager(String password, String phoneNum, String name) {
        super(password, phoneNum, name);
        super.permission = new SimpleStringProperty("Manager");
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