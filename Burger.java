import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.List;

enum Sauce {
    MAYO("Mayo", 0.80),
    KETCHUP("Ketchup", 0.50),
    MUSTARD("Mustard", 0.50),
    RELISH("Relish", 0.80),
    BBQ_SAUCE("BBQ Sauce", 1.00),
    STEAK_SAUCE("Steak Sauce", 1.20),
    HOT_SAUCE("Hot Sauce", 1.00);

    private String name;
    private double price;

    private Sauce(String name, double price) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

enum Meat {
    FISH("Fish", 2.5),
    CHICKEN("Chicken", 2.5),
    BEEF("Beef", 3.5),
    BACON("Bacon", 1.5);

    private String name;
    private double price;

    private Meat(String name, double price) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
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

    private String name;
    private double price;

    private Vegetable(String name, double price) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

enum Side {
    FRIES("Fries", 4.0),
    ONION_RINGS("Onion Rings", 5.0),
    CHICKEN_NUGGETS("Chicken Nuggets", 6.0),
    CHICKEN_WINGS("Chicken Wings", 7.5),
    SALAD("Salad", 3.5);

    private String name;
    private double price;

    private Side(String name, double price) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

enum Drink {
    COKE("Coke", 4.0),
    COKO_ZERO("Coke Zero", 4.0),
    FANTA("Fanta", 4.0),
    SPRITE("Sprite", 4.0),
    ORANGE_JUICE("Orange Juice", 5.0),
    MILK_SHAKE("Milk Shake", 6.0);

    private String name;
    private double price;

    private Drink(String name, double price) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

interface Payment {
    public double countPrice();
}

interface IngredientShowable {
    public void showIngredient();
}

public abstract class Burger implements Payment, IngredientShowable { // An abstract class, Burger class should not be
                                                                      // instantiated
    public static int nextID = 1;
    private List<Sauce> sauces;
    private List<Meat> meats;
    private List<Vegetable> vegetables;
    private int burgerID;
    private String name;
    protected double basePrice;

    public Burger(String name, List<Sauce> sauces, List<Meat> meats, List<Vegetable> vegetables) {
        this.name = name;
        this.sauces = sauces;
        this.meats = meats;
        this.vegetables = vegetables;
        this.burgerID = nextID;
        nextID++;
    }

    public void setSauces(List<Sauce> sauces) {
        this.sauces = sauces;
    }

    public void setMeats(List<Meat> meats) {
        this.meats = meats;
    }

    public void setVegetables(List<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBurgerID(int burgerID) {
        this.burgerID = burgerID;
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
        return burgerID;
    }

    public String getName() {
        return name;
    }

    @Override
    public void showIngredient() { // shows the ingredient inside the burger
        System.out.println("Sauces: ");
        System.out.print("\t");
        if (sauces.size() != 0) {
            for (int i = 0; i < sauces.size() - 1; i++) {
                System.out.print(sauces.get(i).getName() + ", ");
            }
            System.out.print(sauces.get(sauces.size() - 1).getName());
        } else {
            System.out.print("None");
        }
        System.out.println();
        System.out.println("Meats: ");
        System.out.print("\t");
        if (meats.size() != 0) {
            for (int i = 0; i < meats.size() - 1; i++) {
                System.out.print(meats.get(i).getName() + ", ");
            }
            System.out.print(meats.get(meats.size() - 1).getName());
        } else {
            System.out.print("None");
        }
        System.out.println();
        System.out.println("Vegetables: ");
        System.out.print("\t");
        if (vegetables.size() != 0) {
            for (int i = 0; i < vegetables.size() - 1; i++) {
                System.out.print(vegetables.get(i).getName() + ", ");
            }
            System.out.print(vegetables.get(vegetables.size() - 1).getName());
        } else {
            System.out.print("None");
        }
        System.out.println();
    }

    @Override
    public double countPrice() { // count the price of the burger
        double price = basePrice;
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
        for (int i = 0; i < sauces.size() - 1; i++) {
            sauce += sauces.get(i).getName() + ", ";
        }
        for (int i = 0; i < meats.size() - 1; i++) {
            meat += meats.get(i).getName() + ", ";
        }
        for (int i = 0; i < vegetables.size() - 1; i++) {
            vegetable += vegetables.get(i).getName() + ", ";
        }
        sauce += sauces.get(sauces.size() - 1).getName();
        meat += meats.get(meats.size() - 1).getName();
        vegetable += vegetables.get(vegetables.size() - 1).getName();
        return this.name + "\n" + sauce + "\n" + meat + "\n" + vegetable;
    }
}

class WhiteBunBurger extends Burger {

    public WhiteBunBurger(String name, List<Sauce> sauces, List<Meat> meats, List<Vegetable> vegetables) {
        super(name, sauces, meats, vegetables);
        basePrice = 0.5;
    }

    @Override
    public void showIngredient() { // override the method and added bun type
        super.showIngredient();
        System.out.println("Buns: ");
        System.out.println("\tWhite Bun");
        System.out.println();
    }

    @Override
    public String toString() { // override the method and added bun type
        return super.toString() + "\nBuns: White Bun";
    }
}

class SeasameBurger extends Burger {

    public SeasameBurger(String name, List<Sauce> sauces, List<Meat> meats, List<Vegetable> vegetables) {
        super(name, sauces, meats, vegetables);
        basePrice = 1.0;
    }

    @Override
    public void showIngredient() { // override the method and added bun type
        super.showIngredient();
        System.out.println("Buns: ");
        System.out.println("\tSeasame Bun");
        System.out.println();
    }

    @Override
    public String toString() { // override the method and added bun type
        return super.toString() + "\nBuns: Seasame Bun";
    }
}

class GlutenFreeBurger extends Burger {

    public GlutenFreeBurger(String name, List<Sauce> sauces, List<Meat> meats, List<Vegetable> vegetables) {
        super(name, sauces, meats, vegetables);
        basePrice = 2.0;
    }

    @Override
    public void showIngredient() { // override the method and added bun type
        super.showIngredient();
        System.out.println("Buns: ");
        System.out.println("\tGluten Free Bun");
        System.out.println();
    }

    @Override
    public String toString() { // override the method and added bun type
        return super.toString() + "\nBuns: Gluten Free Bun";
    }
}

class Combo {
    private Burger burger;
    private Side side;
    private Drink drink;

    public Combo(Burger burger, Side side, Drink drink) {
        this.burger = burger;
        this.drink = drink;
        this.side = side;
    }

    public double getPrice() {
        double price = 0;
        price += burger.countPrice();
        price += drink.getPrice();
        price += side.getPrice();
        return price;
    }

    public Burger getBurger() {
        return burger;
    }

    public Drink getDrink() {
        return drink;
    }

    public Side getSide() {
        return side;
    }
}

class StaffManagement { // a class to manages all the staffs and manager, it is set to static so manager
                        // and staff and both use this class
    public static Map<Integer, Staff> staffMap = new HashMap<>();

    public static void initStaff(List<Staff> staffs) { // initialized the staff map with a list
        for (Staff s : staffs) {
            staffMap.put(s.getStaffID(), s);
        }
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

    public static Map<Integer, Staff> getStaffMap() {
        return staffMap;
    }

    public static void fireStaff(int id) {
        staffMap.remove(id);
    }

    // Staff class should not have re-assign ID method, because it will confused the
    // staff since they need to use their ID and password to login the system, so
    // it's better leave The ID constant
}

class Recipe {
    public static Map<Integer, Burger> burgerMap = new HashMap<>(); // it is set to static so staff and costomer can
                                                                    // both access it without creating an object

    public static void initRecipe(List<Burger> burger) { // initialized the burger map with a list
        for (Burger b : burger) {
            burgerMap.put(b.getBurgerID(), b);
        }
    }

    public static Map<Integer, Burger> getBurgerMap() {
        return burgerMap;
    }

    public static void delRecipe(int id) {
        burgerMap.remove(id);
    }

    public static void reassignID() { // After we delete some recipe, some ID will be missing, so we need to re-assign
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

class Staff {
    private String password;
    private String phonNum;
    private String name;
    public static int nextID = 1;
    private int staffID;

    public Staff(String password, String phoneNum, String name) {
        this.password = password;
        this.phonNum = phoneNum;
        this.name = name;
        staffID = nextID;
        nextID += 1;
    }

    public void delRecipie(int id, Map<Integer, Burger> burgers) {
        burgers.remove(id);
    }

    public String getName() {
        return name;
    }

    public int getStaffID() {
        return staffID;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonNum() {
        return phonNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhonNum(String phonNum) {
        this.phonNum = phonNum;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public boolean getManagerPermission() { // An overload method, using the return type to finds out if the staff is a
                                            // manager
        return false;
    }

    @Override
    public String toString() {
        if (getManagerPermission()) {
            return "Manager Name: " + this.name + ", Phone Number: " + this.phonNum;
        }
        return "Staff Name: " + this.name + ", Phone Number: " + this.phonNum;
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

class StaffMenu { // the menu for the staff

    public void startMenu() { // Start the Menu
        System.out.println("------------Welcom To The Rim Burger's Management System------------");
        while (true) {
            try {
                System.out.println("Enter Staff ID: ");
                int staffID = In.nextInt();
                System.out.println();
                System.out.println("Enter Password: ");
                String password = In.nextLine();
                System.out.println();
                Staff staff = StaffManagement.login(staffID, password);
                if (staff != null) {
                    System.out.println("Login Sucessful. Hello, " + staff.getName());
                    System.out.println();
                    if (staff.getManagerPermission()) { // If the staff is a manager, it will open the manager menu,
                                                        // otherwise the normal staff menu
                        managerMenu();
                    } else {
                        staffMenu(staff); // a normal staff requires a Staff value because they can only modified their
                                          // own information
                    }
                } else {
                    System.out.println(
                            "Login Failed, Invalid ID or Password. Do You Wanna Try Again? (Y for Yes, Others to Cancel)");
                    char selection = In.nextChar();
                    System.out.println();
                    if (selection == 'Y' || selection == 'y') {
                        continue;
                    } else {
                        break;
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Input");
                In.nextLine();
                System.out.println();
            }
        }
    }

    public void managerMenu() { // Manager Menu
        while (true) {
            System.out.println("Please Select a Option:");
            System.out.println("1. Modify Current Recipe List");
            System.out.println("2. Manage the Staff");
            System.out.println("3. Modify the Ingredients's Price");
            System.out.println("4. Exit");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 4) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 4");
                }
                if (selection == 1) {
                    modRecipe();
                } else if (selection == 2) {
                    manageStaff();
                } else if (selection == 3) {
                    modIngredient();
                } else if (selection == 4) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 4");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void modRecipe() { // Method for removing a recipe from the menu
        while (true) {
            if (Recipe.burgerMap.size() == 0) {
                System.out.println("There is No Recipe Yet, Let Customers Add Some First");
                System.out.println();
                break;
            }
            try {
                System.out.println("Here is Current Recipe List: ");
                System.out.println();
                for (Map.Entry<Integer, Burger> entry : Recipe.burgerMap.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                    System.out.println();
                }
                System.out.println("Please Choose the ID of The Recipe You Want to Delete: ");
                int choice = In.nextInt();
                System.out.println();
                if (choice < 1 || choice > Recipe.burgerMap.size()) {
                    throw new IllegalArgumentException("Invalid Option, Please Select a Valid ID");
                }
                Recipe.delRecipe(choice);
                System.out.println("Deletion Complete");
                System.out.println();
                Recipe.reassignID();// re-assigning ID after removing
                System.out.println("Do You Want to Remove Another One? (Y for Yes, Others for Exit)");
                char selection = In.nextChar();
                System.out.println();
                if (selection == 'Y' || selection == 'y') {
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select a Valid ID");
                In.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void manageStaff() { // Method for managing the staff
        while (true) {
            System.out.println("Please Select a Option:");
            System.out.println("\t1. Add New Staff");
            System.out.println("\t2. Manage Current Staff");
            System.out.println("\t3. Exit");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 3) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 3");
                }
                if (selection == 1) {
                    manageStaffAdd();
                } else if (selection == 2) {
                    manageStaffMod();
                } else if (selection == 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 3");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void manageStaffAdd() { // Method for adding a new staff
        while (true) {
            try {
                System.out.println("Please Input New Staff's Name: ");
                String name = In.nextLine();
                System.out.println();
                System.out.println("Please Input New Staff's Phone Number: ");
                String phoneNum = In.nextLine();
                System.out.println();
                String password = "";
                while (true) {
                    System.out.println("Please Set a Login Password for New Staff: ");
                    password = In.nextLine();
                    System.out.println();
                    System.out.println("Please Confirm the Password");
                    String confirmPassword = In.nextLine();
                    System.out.println();
                    if (!password.equals(confirmPassword)) {
                        System.out.println("Passwords do not Match, Please Try Again");
                        System.out.println();
                        continue;
                    }
                    break;
                }
                Staff s = new Staff(password, phoneNum, name);
                StaffManagement.staffMap.put(s.getStaffID(), s); // Put the new staff in the StaffManagement so it can
                                                                 // be
                                                                 // accessed
                System.out.println("Do You Want to Add Another Staff? (Y for Yes, Others for Exit)");
                char selection = In.nextChar();
                System.out.println();
                if (selection == 'Y' || selection == 'y') {
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Input");
                In.nextLine();
                System.out.println();
            }
        }
    }

    public void manageStaffMod() { // Method for managing the current staff
        while (true) {
            if (StaffManagement.staffMap.size() == 0) {
                System.out.println("There is No Staffs Yet, Add Staff to the System First");
                System.out.println();
                break;
            }
            System.out.println("Here is Current Staff List: ");
            for (Map.Entry<Integer, Staff> entry : StaffManagement.staffMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                System.out.println();
            }
            try {
                System.out.println("Please Choose the ID of The Staff You Want to Manage: ");
                int choice = In.nextInt();
                System.out.println();
                if (!StaffManagement.staffMap.containsKey(choice)) {
                    throw new IllegalArgumentException("Invalid Option, Please Select a Valid ID");
                }
                manageStaffMod2(choice); // open the sub-menu require a int parameter, it will be id for the staff for
                                         // locating them

                System.out.println("Do You Want to Manage Another Staff? (Y for Yes, Others for Exit)");
                char selection = In.nextChar();
                System.out.println();
                if (selection == 'Y' || selection == 'y') {
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select a Valid ID");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void manageStaffMod2(int choice) { // the sub menu for manageStaffmod
        while (true) {
            System.out.println("Please Choose an Action for this Staff: ");
            System.out.println("\t1. Fire this Staff");
            System.out.println("\t2. Change this Staff's Personal Information");
            System.out.println("\t3. Promote this Staff to a Manager");
            System.out.println("\t4. Return to Previous Step");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 4) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 4");
                }
                if (selection == 1) {
                    StaffManagement.fireStaff(choice); // Removing staff
                    break;
                } else if (selection == 2) {
                    manageStaffMod3(choice); // open the sub-menu with the staff id as the parameter
                } else if (selection == 3) {
                    Staff s = new Manager(StaffManagement.staffMap.get(choice).getPassword(), // Use the staff
                                                                                              // information to ceate a
                                                                                              // new
                                                                                              // manager object,
                                                                                              // removing the old staff
                                                                                              // object and change it
                                                                                              // with the new
                                                                                              // manager object to
                                                                                              // promote the staff
                            StaffManagement.staffMap.get(choice).getPhonNum(),
                            StaffManagement.staffMap.get(choice).getName());
                    StaffManagement.staffMap.remove(choice);
                    StaffManagement.staffMap.put(choice, s);
                    System.out.println("Staff has been Promoted");
                } else if (selection == 4) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 4");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void manageStaffMod3(int choice) { // sub-menu for manageStaffMod2, change a specific staff's personal
                                              // information
        while (true) {
            System.out.println("Please Choose the Information You Want to Change: ");
            System.out.println("\t1. Name");
            System.out.println("\t2. Phone Number");
            System.out.println("\t3. Password");
            System.out.println("\t4. Return to Previous Step");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 4) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 4");
                }
                if (selection == 1) {
                    System.out.println("Please Input a New Name: ");
                    String newName = In.nextLine();
                    if (newName.equals("")) {
                        throw new IllegalArgumentException("Name Cannot be Blank");
                    }
                    System.out.println();
                    StaffManagement.staffMap.get(choice).setName(newName);
                } else if (selection == 2) {
                    System.out.println("Please Input a New Phone Number: ");
                    String newPhoneNum = In.nextLine();
                    if (newPhoneNum.equals("")) {
                        throw new IllegalArgumentException("Phone Number Cannot be Blank");
                    }
                    System.out.println();
                    StaffManagement.staffMap.get(choice).setPhonNum(newPhoneNum);
                } else if (selection == 3) {
                    while (true) {
                        System.out.println("please Input a New Password: ");
                        String newPassword = In.nextLine();
                        if (newPassword.equals("")) {
                            throw new IllegalArgumentException("Password Cannot be Blank");
                        }
                        System.out.println();
                        System.out.println("Please Confirm the Password");
                        String confirmPassword = In.nextLine();
                        System.out.println();
                        if (!newPassword.equals(confirmPassword)) {
                            System.out.println("Passwords do not Match, Please Try Again");
                            continue;
                        }
                        StaffManagement.staffMap.get(choice).setPassword(newPassword);
                        break;
                    }
                } else if (selection == 4) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 4");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void modIngredient() { // Change the ingredient price
        while (true) {
            System.out.println("Please Select the Category of the Ingredient:");
            System.out.println("1. Sauce");
            System.out.println("2. Meat");
            System.out.println("3. Vegetable");
            System.out.println("4. Side");
            System.out.println("5. Drink");
            System.out.println("6. Exit");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 6) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 6");
                }
                if (selection == 1) {
                    sauceModMenu();
                } else if (selection == 2) {
                    meatModMenu();
                } else if (selection == 3) {
                    vegetableModMenu();
                } else if (selection == 4) {
                    sideModMenu();
                } else if (selection == 5) {
                    drinkModMenu();
                } else if (selection == 6) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 6");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void sauceModMenu() {
        Sauce[] sauces = Sauce.values();
        while (true) {
            System.out.println("Please Choose the Number of The Sauce You Want to Modify: ");
            for (int i = 0; i < sauces.length; i++) {
                System.out.println(i + 1 + ". " + sauces[i].getName());
            }
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > sauces.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                Sauce s = sauces[selection - 1];
                System.out.println("Please Input the New Price for the Ingredient: ");
                double newPrice = In.nextDouble();
                System.out.println();
                s.setPrice(newPrice);
                System.out.println("Modification Sucessful");
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void meatModMenu() {
        Meat[] meats = Meat.values();
        while (true) {
            System.out.println("Please Choose the Number of The Meat You Want to Modify: ");
            for (int i = 0; i < meats.length; i++) {
                System.out.println(i + 1 + ". " + meats[i].getName());
            }
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > meats.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                Meat s = meats[selection - 1];
                System.out.println("Please Input the New Price for the Ingredient: ");
                double newPrice = In.nextDouble();
                System.out.println();
                s.setPrice(newPrice);
                System.out.println("Modification Sucessful");
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void vegetableModMenu() {
        Vegetable[] vegetables = Vegetable.values();
        while (true) {
            System.out.println("Please Choose the Number of The Vegetable You Want to Modify: ");
            for (int i = 0; i < vegetables.length; i++) {
                System.out.println(i + 1 + ". " + vegetables[i].getName());
            }
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > vegetables.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                Vegetable s = vegetables[selection - 1];
                System.out.println("Please Input the New Price for the Ingredient: ");
                double newPrice = In.nextDouble();
                System.out.println();
                s.setPrice(newPrice);
                System.out.println("Modification Sucessful");
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void sideModMenu() {
        Side[] sides = Side.values();
        while (true) {
            System.out.println("Please Choose the Number of The Side You Want to Modify: ");
            for (int i = 0; i < sides.length; i++) {
                System.out.println(i + 1 + ". " + sides[i].getName());
            }
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > sides.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                Side s = sides[selection - 1];
                System.out.println("Please Input the New Price for the Ingredient: ");
                double newPrice = In.nextDouble();
                System.out.println();
                s.setPrice(newPrice);
                System.out.println("Modification Sucessful");
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void drinkModMenu() {
        Drink[] drinks = Drink.values();
        while (true) {
            System.out.println("Please Choose the Number of The Drink You Want to Modify: ");
            for (int i = 0; i < drinks.length; i++) {
                System.out.println(i + 1 + ". " + drinks[i].getName());
            }
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > drinks.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                Drink s = drinks[selection - 1];
                System.out.println("Please Input the New Price for the Ingredient: ");
                double newPrice = In.nextDouble();
                System.out.println();
                s.setPrice(newPrice);
                System.out.println("Modification Sucessful");
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void staffMenu(Staff staff) { // Menu for normal staff
        while (true) {
            System.out.println("Please Select a Option:");
            System.out.println("1. Modify Current Recipe List");
            System.out.println("2. Manage Personal Information");
            System.out.println("3. Exit");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 4) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 4");
                }
                if (selection == 1) {
                    modRecipe();
                } else if (selection == 2) {
                    manageInfo(staff); // work like manageStaffMod3, but this time only changing the information for
                                       // given Staff
                } else if (selection == 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 3");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void manageInfo(Staff staff) {
        while (true) {
            System.out.println("Please Choose the Information You Want to Change: ");
            System.out.println("\t1. Name");
            System.out.println("\t2. Phone Number");
            System.out.println("\t3. Password");
            System.out.println("\t4. Return to Previous Step");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 4) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 4");
                }
                if (selection == 1) {
                    System.out.println("Please Input a New Name: ");
                    String newName = In.nextLine();
                    if (newName.equals("")) {
                        throw new IllegalArgumentException("Name Cannot be Blank");
                    }
                    System.out.println();
                    staff.setName(newName);
                } else if (selection == 2) {
                    System.out.println("Please Input a New Phone Number: ");
                    String newPhoneNum = In.nextLine();
                    if (newPhoneNum.equals("")) {
                        throw new IllegalArgumentException("Phone Number Cannot be Blank");
                    }
                    System.out.println();
                    staff.setPhonNum(newPhoneNum);
                } else if (selection == 3) {
                    while (true) {
                        System.out.println("please Input a New Password: ");
                        String newPassword = In.nextLine();
                        if (newPassword.equals("")) {
                            throw new IllegalArgumentException("Password Cannot be Blank");
                        }
                        System.out.println();
                        System.out.println("Please Confirm the Password");
                        String confirmPassword = In.nextLine();
                        System.out.println();
                        if (!newPassword.equals(confirmPassword)) {
                            System.out.println("Passwords do not Match, Please Try Again");
                            System.out.println();
                            continue;
                        }
                        staff.setPassword(newPassword);
                        break;
                    }
                } else if (selection == 4) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 4");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
}

class CustomerMenu { // Menu for Customer
    private static Comparator<Burger> comparePriceD = Comparator.comparing(Burger::countPrice).reversed();
    private static Comparator<Burger> comparePriceA = Comparator.comparing(Burger::countPrice);
    private static Comparator<Burger> compareAlphabate = Comparator.comparing(Burger::getName); // Some comparator to
                                                                                                // sort the menu
    private static Comparator<Burger> compareID = Comparator.comparing(Burger::getBurgerID);

    public void startMenu() {
        System.out.println("------------Welcom To The Rim Burger------------");
        while (true) {
            System.out.println("Please Select a Option:");
            System.out.println("1. Taking Order");
            System.out.println("2. Exit");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 2) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 4");
                }
                if (selection == 1) {
                    takeOrder();
                } else if (selection == 2) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 4");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void takeOrder() {
        while (true) {
            System.out.println("Please Select a Option:");
            System.out.println("1. Ordering from Current Menu");
            System.out.println("2. Customize Your Own Burger Recipe");
            System.out.println("3. Return to the Previous Step");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 3) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 3");
                }
                if (selection == 1) {
                    orderBurger();
                } else if (selection == 2) {
                    customizeBurger();
                } else if (selection == 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 3");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void orderBurger() {
        while (true) {
            if (Recipe.burgerMap.size() == 0) {
                System.out.println(
                        "Sorry! Currently There is No Recipie on the Menu, But You Are Welcom to be the First Recipe Uploader! :)");
                System.out.println();
                break;
            }
            List<Burger> burgerList = new ArrayList<>();
            for (Burger b : Recipe.burgerMap.values()) {
                burgerList.add(b); // use the map from the Recipe class to make a list for sorting
            }
            System.out.println("How Would You like to Sort the Menu?");
            System.out.println("\t1. Sort by ID");
            System.out.println("\t2. Sort by Name");
            System.out.println("\t3. Sort by Price (Acsending)");
            System.out.println("\t4. Sort by Price (Descening)");
            System.out.println("\t5. Return to the previous step");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 5) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 5");
                }
                if (selection == 1) {
                    Collections.sort(burgerList, CustomerMenu.compareID);
                    orderBurger2(burgerList);
                } else if (selection == 2) {
                    Collections.sort(burgerList, CustomerMenu.compareAlphabate);
                    orderBurger2(burgerList);
                } else if (selection == 3) {
                    Collections.sort(burgerList, CustomerMenu.comparePriceA);
                    orderBurger2(burgerList);
                } else if (selection == 4) {
                    Collections.sort(burgerList, CustomerMenu.comparePriceD);
                    orderBurger2(burgerList);
                } else if (selection == 5) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 5");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void orderBurger2(List<Burger> burgers) {
        System.out.println("Here is Current Menu: ");
        for (Burger b : burgers) {
            System.out.println(b.getBurgerID() + " " + b);
            System.out.println();
        }
        while (true) {
            try {
                System.out.println(
                        "Please Choose the ID of The Burger You Want to Order or 0 to Return to Previous Step: ");
                int choice = In.nextInt();
                System.out.println();
                if (choice < 0 || choice > Recipe.burgerMap.size()) {
                    throw new IllegalArgumentException("Invalid Option, Please Select a Valid ID");
                }
                if (choice == 0) {
                    break;
                }
                Recipe.burgerMap.get(choice).showIngredient();

                System.out.println("Do You Want Move Forward with this Burger? (Y for Yes, Others for Choose Another)");
                char selection = In.nextChar();
                System.out.println();
                if (!(selection == 'Y' || selection == 'y')) {
                    continue;
                }
                System.out.println("Do You Want to Make it a Combo? (Y for Yes, Others for Order Individually)");
                char option = In.nextChar();
                System.out.println();
                if (option == 'Y' || option == 'y') {
                    makeCombo(Recipe.burgerMap.get(choice)); // Making a combo object first with a drink and side
                                                             // before goes to the payment page
                } else {
                    payment(Recipe.burgerMap.get(choice)); // Goes to the payment directly with only the burger
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select a Valid ID");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void makeCombo(Burger b) { // Make the burger a combo
        Drink[] drinks = Drink.values();
        Side[] sides = Side.values();
        Drink drink = null;
        Side side = null;
        while (true) {
            try {
                System.out.println("Please Choose the Side You Want to Order: ");
                for (int i = 0; i < sides.length; i++) {
                    System.out.println(i + 1 + ". " + sides[i].getName());
                }
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > sides.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                side = sides[selection - 1];
                System.out.println("Please Choose the Drink You Want to Order: ");
                for (int i = 0; i < drinks.length; i++) {
                    System.out.println(i + 1 + ". " + drinks[i].getName());
                }
                int option = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > drinks.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                drink = drinks[option - 1];
                Combo c = new Combo(b, side, drink);
                System.out.println("You Selected: " + drink.getName() + " for Your Drink, and " + side.getName()
                        + " for Your Side.");
                System.out.println("Do You Want Move Forward to Payment? (Y for Yes, Others for Choosing Again)");
                char choice = In.nextChar();
                System.out.println();
                if (choice == 'Y' || choice == 'y') {
                    payment(c);
                } else {
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void customizeBurger() {
        List<Meat> meats = new ArrayList<>();
        List<Vegetable> vegetables = new ArrayList<>();
        List<Sauce> sauces = new ArrayList<>();
        while (true) {
            System.out.println("Please Choose the Ingredients You want");
            System.out.println("\n1. Meats");
            System.out.println("\n2. Vegetables");
            System.out.println("\n3. Sauces");
            System.out.println("\n4: Remove Ingredients");
            System.out.println("\n5: Finish Customization");
            System.out.println("\n6. Return to the Previous Step");
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 6) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 6");
                }
                if (selection == 1) {
                    addMeats(meats);
                } else if (selection == 2) {
                    addVegetable(vegetables);
                } else if (selection == 3) {
                    addSauce(sauces);
                } else if (selection == 4) {
                    removeIngredients(meats, vegetables, sauces);
                } else if (selection == 5) {
                    finalizeCustom(meats, vegetables, sauces);
                } else if (selection == 6) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 6");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void addMeats(List<Meat> meatList) {
        Meat[] meats = Meat.values();
        System.out.println("Please Choose the Number of The Meats You Want to Add (Press 0 to Stop Adding): ");
        for (int i = 0; i < meats.length; i++) {
            System.out.println(i + 1 + ". " + meats[i].getName());
        }
        while (true) {
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection == 0) {
                    break;
                }
                if (selection < 1 || selection > meats.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                Meat s = meats[selection - 1];
                meatList.add(s);
                System.out.println("Adding Successful");
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void addVegetable(List<Vegetable> vegetableList) {
        Vegetable[] vegetables = Vegetable.values();
        System.out.println("Please Choose the Number of The Vegetable You Want to Add (Press 0 to Stop Adding): ");
        for (int i = 0; i < vegetables.length; i++) {
            System.out.println(i + 1 + ". " + vegetables[i].getName());
        }
        while (true) {
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection == 0) {
                    break;
                }
                if (selection < 1 || selection > vegetables.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                Vegetable s = vegetables[selection - 1];
                vegetableList.add(s);
                System.out.println("Adding Successful");
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void addSauce(List<Sauce> sauceList) {
        Sauce[] sauces = Sauce.values();
        System.out.println("Please Choose the Number of The Sauce You Want to Add (Press 0 to Stop Adding): ");
        for (int i = 0; i < sauces.length; i++) {
            System.out.println(i + 1 + ". " + sauces[i].getName());
        }
        while (true) {
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection == 0) {
                    break;
                }
                if (selection < 1 || selection > sauces.length) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                Sauce s = sauces[selection - 1];
                sauceList.add(s);
                System.out.println("Adding Successful");
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void removeIngredients(List<Meat> meatList, List<Vegetable> vegetableList, List<Sauce> sauceList) {
        while (true) {
            System.out.println("Please Choose the Ingredients You Want to Remove");
            System.out.println("\n1. Meats");
            System.out.println("\n2. Vegetables");
            System.out.println("\n3. Sauces");
            System.out.println("\n4: Retrun to Previous Step");

            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 4) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 4");
                }
                if (selection == 1) {
                    rmMeats(meatList);
                } else if (selection == 2) {
                    rmVegetables(vegetableList);
                } else if (selection == 3) {
                    rmSauce(sauceList);
                } else if (selection == 4) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Option, Please Select Between 1 ~ 4");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void rmMeats(List<Meat> meatList) { // This method remove meats from the customize burger
        if (meatList.size() == 0) {
            System.out.println("Current Recipe Doesn't Contain Any Meats");
            return;
        }
        System.out.println("Please Choose the Number of The Meats You Want to Remove (Press 0 to Stop Removing): ");
        for (int i = 0; i < meatList.size(); i++) {
            System.out.println(i + 1 + ". " + meatList.get(i).getName());
        }
        while (true) {
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection == 0) {
                    break;
                }
                if (selection < 1 || selection > meatList.size()) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                meatList.remove(selection - 1);
                System.out.println("Removing Successful");
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void rmVegetables(List<Vegetable> vegetableList) { // This method remove vegetables from the customize burger
        if (vegetableList.size() == 0) {
            System.out.println("Current Recipe Doesn't Contain Any Vegetables");
            return;
        }
        System.out
                .println("Please Choose the Number of The Vegetables You Want to Remove (Press 0 to Stop Removing): ");
        for (int i = 0; i < vegetableList.size(); i++) {
            System.out.println(i + 1 + ". " + vegetableList.get(i).getName());
        }
        while (true) {
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection == 0) {
                    break;
                }
                if (selection < 1 || selection > vegetableList.size()) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                vegetableList.remove(selection - 1);
                System.out.println("Removing Successful");
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void rmSauce(List<Sauce> sauceList) { // This method remove sauces from the customize burger
        if (sauceList.size() == 0) {
            System.out.println("Current Recipe Doesn't Contain Any Sauces");
            return;
        }
        System.out.println("Please Choose the Number of The Sauces You Want to Remove (Press 0 to Stop Removing): ");
        for (int i = 0; i < sauceList.size(); i++) {
            System.out.println(i + 1 + ". " + sauceList.get(i).getName());
        }
        while (true) {
            try {
                int selection = In.nextInt();
                System.out.println();
                if (selection == 0) {
                    break;
                }
                if (selection < 1 || selection > sauceList.size()) {
                    throw new IllegalArgumentException("Invalid Number");
                }
                sauceList.remove(selection - 1);
                System.out.println("Removing Successful");
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid Number");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void finalizeCustom(List<Meat> meatList, List<Vegetable> vegetableList, List<Sauce> sauceList) {
        while (true) {
            try {
                boolean makeCombo = false;
                String name = null;

                System.out.println("Do You want Move Forward with this Recipe? (Y for Yes, Others for Edit Again)");
                char choice = In.nextChar();
                System.out.println();
                if (!(choice == 'Y' || choice == 'y')) {
                    break;
                }

                System.out.println(
                        "Do You want to Upload Your Recipe to the Public Menu? (Y for Yes, Others for Decline)");
                char option = In.nextChar();
                System.out.println();
                if (option == 'Y' || option == 'y') {
                    System.out.println("Please Enter the Name: ");
                    name = In.nextLine();
                }
                System.out.println();

                System.out.println("Do You want to Make Your Burger a Combo? (Y for Yes, Others for Decline)");
                char selection = In.nextChar();
                System.out.println();
                if (selection == 'Y' || selection == 'y') {
                    makeCombo = true;
                }

                System.out.println("Please Choose the Bun You want");
                System.out.println("\n1. White Bun");
                System.out.println("\n2. Seasame Bun");
                System.out.println("\n3. Gulten Free Bun");
                int choose = In.nextInt();
                System.out.println();
                if (choose < 1 || choose > 3) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 3");
                }
                if (choose == 1) {
                    Burger b = new WhiteBunBurger(name, sauceList, meatList, vegetableList);
                    if (name != null) {
                        Recipe.burgerMap.put(b.getBurgerID(), b);
                    } else {
                        Burger.nextID--;
                    }

                    if (makeCombo) {
                        makeCombo(b);
                    } else {
                        payment(b);
                    }
                } else if (choose == 2) {
                    Burger b = new SeasameBurger(name, sauceList, meatList, vegetableList);
                    if (name != null) {
                        Recipe.burgerMap.put(b.getBurgerID(), b);
                    } else {
                        Burger.nextID--;
                    }

                    if (makeCombo) {
                        makeCombo(b);
                    } else {
                        payment(b);
                    }
                } else if (choose == 3) {
                    Burger b = new GlutenFreeBurger(name, sauceList, meatList, vegetableList);
                    if (name != null) {
                        Recipe.burgerMap.put(b.getBurgerID(), b);
                    } else {
                        Burger.nextID--;
                    }

                    if (makeCombo) {
                        makeCombo(b);
                    } else {
                        payment(b);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option, Please Select Between 1 ~ 4");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void payment(Combo c) {
        while (true) {
            try {
                System.out.println("Your Burger's Ingredient: ");
                c.getBurger().showIngredient();
                System.out.println();
                System.out.println("Your Combo's Detail: ");
                System.out.println("Drink: " + c.getDrink().getName());
                System.out.println("Side: " + c.getSide().getName());
                System.out.println();
                System.out.println("Final Combo Price: $" + c.getPrice());
                System.out.println();

                System.out.println("Please Choose the Payment Method");
                System.out.println("\n1. Card");
                System.out.println("\n2. Pay At the Front");
                System.out.println("\n3. Cancel Order");
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 4) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 3");
                }
                if (selection == 1) {
                    System.out.println("Processing payment...");
                    System.out.println("Payment Successful! Enjoy your meal");
                    System.exit(0); // I'm sorry I know we haven't learn this but I think it's way too hard to make
                                    // these sub-menu run properly without this
                } else if (selection == 2) {
                    System.out.println("Please Go to the Front to Finish the Payment");
                    System.exit(0);
                } else if (selection == 3) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option, Please Select Between 1 ~ 3");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public void payment(Burger b) {
        while (true) {
            try {
                System.out.println("Your Burger's Ingredient: ");
                b.showIngredient();
                System.out.println();
                System.out.println("Final Burger Price: $" + b.countPrice());
                System.out.println();

                System.out.println("Please Choose the Payment Method");
                System.out.println("\n1. Card");
                System.out.println("\n2. Pay At the Front");
                System.out.println("\n3. Cancel Order");
                int selection = In.nextInt();
                System.out.println();
                if (selection < 1 || selection > 4) {
                    throw new IllegalArgumentException("Invalid Option, Please Select Between 1 ~ 3");
                }
                if (selection == 1) {
                    System.out.println("Processing payment...");
                    System.out.println("Payment Successful! Enjoy your meal");
                    System.exit(0);
                } else if (selection == 2) {
                    System.out.println("Please Go to the Front to Finish the Payment");
                    System.exit(0);
                } else if (selection == 3) {
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option, Please Select Between 1 ~ 3");
                In.nextLine();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Staff john = new Manager("password", "1234567", "John");
        StaffManagement.staffMap.put(john.getStaffID(), john);

        // StaffMenu sm = new StaffMenu();
        // sm.startMenu();
        CustomerMenu cm = new CustomerMenu();
        cm.startMenu();
    }
}