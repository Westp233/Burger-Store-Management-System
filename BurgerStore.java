import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BurgerStore extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rim Burger");
        BurgerStoreModel model = new BurgerStoreModel();
        BurgerStoreView view = new BurgerStoreView(model, primaryStage);
        BurgerStoreController controller = new BurgerStoreController(model, view); //if we want the controller to control the view, the controller also needs to have the view
        view.setController(controller); // However it will be hard to initialize so I add a setController to pass the controller to the view separately.

        Staff admin = new Manager("password", "1234567", "Admin");
        List<Staff> defaultStaffs = new ArrayList<Staff>();
        defaultStaffs.add(admin);

        model.initStaff(defaultStaffs);

        List<Sauce> sauces = new ArrayList<>(Arrays.asList(Sauce.MAYO, Sauce.KETCHUP, Sauce.MUSTARD));
        List<Meat> meats = new ArrayList<>(Arrays.asList(Meat.BEEF, Meat.BACON));
        List<Vegetable> veggies = new ArrayList<>(
                Arrays.asList(Vegetable.LETTUCE, Vegetable.TOMATO, Vegetable.ONIONS, Vegetable.PICKLES));
        WhiteBunBurger b = new WhiteBunBurger("Classic Beef Burger", sauces, meats, veggies);
        List<Burger> burgers = new ArrayList<>(Arrays.asList(b));

        model.initRecipe(burgers);

        Scene scene = new Scene(view.asParent(), 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
