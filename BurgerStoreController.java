import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;

public class BurgerStoreController {
    private final BurgerStoreModel model;
    private final BurgerStoreView view;

    public BurgerStoreController(BurgerStoreModel model, BurgerStoreView view) {
        this.model = model;
        this.view = view;
    }

    public void login(String id, String password) { // A controller method interact with the view directly
        try { // An catcher in case the user input a string at idField
            Staff staff = model.login(Integer.parseInt(id.trim()), password.trim());
            if (!(staff == null)) {
                if (staff.getPermission().equals("Manager")) { // Check if the staff is manager to decide which menu we
                                                               // open
                    view.createManagerMenu(staff);
                } else {
                    view.createStaffMenu(staff);
                }
            } else {
                view.createLoginErrorPage();// If the information provided is invalid, show a login failed window
            }
        } catch (NumberFormatException e) {
            view.createLoginErrorPage();
        }
    }

    public void removeRecipe(int index) { // Remove the burger with provided burger
        model.removeRecipe(index);
    }

    public void setPrice(Meat meat, String price) {// A controller method interact with the view directly if there's
                                                   // invalid input
        try { // An catcher in case the user input a string at TextField
            int newPrice = Integer.parseInt(price.trim());
            if (newPrice < 0) {
                throw new IllegalArgumentException();
            } else {
                meat.setPrice(newPrice);
            }
        } catch (NumberFormatException e) {
            view.createPriceErrorPage();
        } catch (IllegalArgumentException e) {
            view.createPriceErrorPage();
        }
    }

    public void setPrice(Sauce sauce, String price) {// A controller method interact with the view directly if there's
                                                     // invalid input
        try { // An catcher in case the user input a string at TextField
            int newPrice = Integer.parseInt(price.trim());
            if (newPrice < 0) {
                throw new IllegalArgumentException();
            } else {
                sauce.setPrice(newPrice);
            }
        } catch (NumberFormatException e) {
            view.createPriceErrorPage();
        } catch (IllegalArgumentException e) {
            view.createPriceErrorPage();
        }
    }

    public void setPrice(Vegetable vegetable, String price) { // A controller method interact with the view directly if
                                                              // there's invalid input
        try { // An catcher in case the user input a string at TextField
            int newPrice = Integer.parseInt(price.trim());
            if (newPrice < 0) {
                throw new IllegalArgumentException();
            } else {
                vegetable.setPrice(newPrice);
            }
        } catch (NumberFormatException e) {
            view.createPriceErrorPage();
        } catch (IllegalArgumentException e) {
            view.createPriceErrorPage();
        }
    }

    public void setPrice(Drink drink, String price) { // A controller method interact with the view directly if there's
                                                      // invalid input
        try { // An catcher in case the user input a string at TextField
            int newPrice = Integer.parseInt(price.trim());
            if (newPrice < 0) {
                throw new IllegalArgumentException();
            } else {
                drink.setPrice(newPrice);
            }
        } catch (NumberFormatException e) {
            view.createPriceErrorPage();
        } catch (IllegalArgumentException e) {
            view.createPriceErrorPage();
        }
    }

    public void setPrice(Side side, String price) { // A controller method interact with the view directly if there's
                                                    // invalid input
        try { // An catcher in case the user input a string at TextField
            int newPrice = Integer.parseInt(price.trim());
            if (newPrice < 0) {
                throw new IllegalArgumentException();
            } else {
                side.setPrice(newPrice);
            }
        } catch (NumberFormatException e) {
            view.createPriceErrorPage();
        } catch (IllegalArgumentException e) {
            view.createPriceErrorPage();
        }
    }

    public void removeStaff(int index) { // remove a staff from the observable list with given index
        model.removeStaff(index);
    }

    public void createStaff(String name, String phoneNum, String password, String confirmation) { // create a staff
                                                                                                  // Object and add it
                                                                                                  // to the list, make
                                                                                                  // view shows error
                                                                                                  // page if user leave
                                                                                                  // the information
                                                                                                  // part empty or
                                                                                                  // password is not
                                                                                                  // smae
        name = name.trim();
        phoneNum = phoneNum.trim();
        password = password.trim();
        confirmation = confirmation.trim();
        if (password.isEmpty() || phoneNum.isEmpty() || password.isEmpty() || confirmation.isEmpty()) {
            view.createEmptyErrorPage();
        } else if (!(password.equals(confirmation))) {
            view.createPasswordErrorPage();
        } else {
            Staff s = new Staff(password, phoneNum, name);
            model.addStaff(s);
        }
    }

    public void editStaff(Staff s, String name, String phoneNum, String password, String confirmation, // edit a staff's
                                                                                                       // personal
                                                                                                       // information
            String permission) {
        name = name.trim();
        phoneNum = phoneNum.trim();
        password = password.trim();
        confirmation = confirmation.trim();
        if (password.isEmpty() || phoneNum.isEmpty() || password.isEmpty() || confirmation.isEmpty()) {
            view.createEmptyErrorPage();
        } else if (!(password.equals(confirmation))) {
            view.createPasswordErrorPage();
        } else {
            s.setName(name);
            s.setPassword(password);
            s.setPhonNum(phoneNum);
            s.setPermission(permission);
        }
    }

    public void sortID() { // some corting method for the burgers list
        Comparator<Burger> compareID = Comparator.comparing(Burger::getBurgerID);
        FXCollections.sort(model.burgersProperty(), compareID);
    }

    public void sortName() {
        Comparator<Burger> compareName = Comparator.comparing(Burger::getName);
        FXCollections.sort(model.burgersProperty(), compareName);
    }

    public void sortPrice() {
        Comparator<Burger> comparePriceA = Comparator.comparing(Burger::countPrice);
        FXCollections.sort(model.burgersProperty(), comparePriceA);
    }

    public void sortPriceR() {
        Comparator<Burger> comparePriceD = Comparator.comparing(Burger::countPrice).reversed();
        FXCollections.sort(model.burgersProperty(), comparePriceD);
    }

    public void createBurger(String bun, // method for contomize burger, creating a burger with user's input, if user
                                         // input a string, show an error page
            String fish, String chicken, String beef, String bacon,
            String lettuce, String tomato, String onion, String grilledOnion, String grilledMushroom,
            String jalapeno, String greenPepper, String pickle,
            String mayo, String ketchup, String mustard, String relish, String bbq, String steak, String hot) {
        try {
            int fishAmount = Integer.parseInt(fish.trim());
            int chickenAmount = Integer.parseInt(chicken.trim());
            int beefAmount = Integer.parseInt(beef.trim());
            int baconAmount = Integer.parseInt(bacon.trim());

            int lettuceAmount = Integer.parseInt(lettuce.trim());
            int tomatoAmount = Integer.parseInt(tomato.trim());
            int onionAmount = Integer.parseInt(onion.trim());
            int grilledOnionAmount = Integer.parseInt(grilledOnion.trim());
            int grilledMushroomAmount = Integer.parseInt(grilledMushroom.trim());
            int jalapenoAmount = Integer.parseInt(jalapeno.trim());
            int greenPepperAmount = Integer.parseInt(greenPepper.trim());
            int pickleAmount = Integer.parseInt(pickle.trim());

            int mayoAmount = Integer.parseInt(mayo.trim());
            int ketchupAmount = Integer.parseInt(ketchup.trim());
            int mustardAmount = Integer.parseInt(mustard.trim());
            int relishAmount = Integer.parseInt(relish.trim());
            int bbqAmount = Integer.parseInt(bbq.trim());
            int steakAmount = Integer.parseInt(steak.trim());
            int hotAmount = Integer.parseInt(hot.trim());

            List<Meat> meats = new ArrayList<>();
            for (int i = 0; i < fishAmount; i++) {
                meats.add(Meat.FISH);
            }
            for (int i = 0; i < chickenAmount; i++) {
                meats.add(Meat.CHICKEN);
            }
            for (int i = 0; i < beefAmount; i++) {
                meats.add(Meat.BEEF);
            }
            for (int i = 0; i < baconAmount; i++) {
                meats.add(Meat.BACON);
            }

            List<Vegetable> vegetables = new ArrayList<>();
            for (int i = 0; i < lettuceAmount; i++) {
                vegetables.add(Vegetable.LETTUCE);
            }
            for (int i = 0; i < tomatoAmount; i++) {
                vegetables.add(Vegetable.TOMATO);
            }
            for (int i = 0; i < onionAmount; i++) {
                vegetables.add(Vegetable.ONIONS);
            }
            for (int i = 0; i < grilledOnionAmount; i++) {
                vegetables.add(Vegetable.GRILLED_ONIONS);
            }
            for (int i = 0; i < grilledMushroomAmount; i++) {
                vegetables.add(Vegetable.GRILLED_MUSHROOMS);
            }
            for (int i = 0; i < jalapenoAmount; i++) {
                vegetables.add(Vegetable.JALAPENO);
            }
            for (int i = 0; i < greenPepperAmount; i++) {
                vegetables.add(Vegetable.GREEN_PEPPERS);
            }
            for (int i = 0; i < pickleAmount; i++) {
                vegetables.add(Vegetable.PICKLES);
            }

            List<Sauce> sauces = new ArrayList<>();
            for (int i = 0; i < mayoAmount; i++) {
                sauces.add(Sauce.MAYO);
            }
            for (int i = 0; i < ketchupAmount; i++) {
                sauces.add(Sauce.KETCHUP);
            }
            for (int i = 0; i < mustardAmount; i++) {
                sauces.add(Sauce.MUSTARD);
            }
            for (int i = 0; i < relishAmount; i++) {
                sauces.add(Sauce.RELISH);
            }
            for (int i = 0; i < bbqAmount; i++) {
                sauces.add(Sauce.BBQ_SAUCE);
            }
            for (int i = 0; i < steakAmount; i++) {
                sauces.add(Sauce.STEAK_SAUCE);
            }
            for (int i = 0; i < hotAmount; i++) {
                sauces.add(Sauce.HOT_SAUCE);
            }

            Burger b = null;
            if (bun.equals("White Bun")) {
                b = new WhiteBunBurger("No Name", sauces, meats, vegetables);
            } else if (bun.equals("Seasame Bun")) {
                b = new SeasameBurger("No Name", sauces, meats, vegetables);
            } else {
                b = new GlutenFreeBurger("No Name", sauces, meats, vegetables);
            }

            view.createFinalizationPage(b);

        } catch (NumberFormatException e) {
            view.createBurgerErrorPage();
        }
    }

    public void removeRecentRecipe() { // if a customer don't want to upload his recipe, since the nextID always
                                       // increment when new object is created, we need to -1
        Burger.nextID--;
    }

    public void uploadRecipe(Burger b) { // add a burger to the recipe
        model.addRecipe(b);
    }

    public void setBurgerName(Burger b, String s) { // set a name to the given burger
        b.setName(s);
    }

    public void createCombo(Burger b, Side s, Drink d) { // create a combo and directly interact with view to create a
                                                         // new page
        Combo c = new Combo(b, s, d);
        view.createPaymentPage(c);
    }
}