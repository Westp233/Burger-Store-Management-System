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

    public void removeRecipe(int index) {
        model.removeRecipe(index);
    }

    public void setPrice(Meat meat, String price) {
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

    public void setPrice(Sauce sauce, String price) {
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

    public void setPrice(Vegetable vegetable, String price) {
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

    public void setPrice(Drink drink, String price) {
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

    public void setPrice(Side side, String price) {
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

    public void removeStaff(int index) {
        model.removeStaff(index);
    }

    public void createStaff(String name, String phoneNum, String password, String confirmation) {
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

    public void editStaff(Staff s, String name, String phoneNum, String password, String confirmation,
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
}