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
        
        

        Staff john = new Manager("password", "1234567", "John");
        Staff carl = new Staff("password", "1234567", "Carl");
        List<Staff> defaultStaffs = new ArrayList<Staff>();
        defaultStaffs.add(john);
        defaultStaffs.add(carl);

        model.initStaff(defaultStaffs);

        List<Sauce> sauces = new ArrayList<>(Arrays.asList(Sauce.MAYO));
        List<Meat> meats = new ArrayList<>();
        List<Meat> meats2 = new ArrayList<>(Arrays.asList(Meat.BACON, Meat.BACON, Meat.BACON, Meat.BACON, Meat.BACON));
        List<Vegetable> veggues = new ArrayList<>(Arrays.asList(Vegetable.GREEN_PEPPERS, Vegetable.GRILLED_MUSHROOMS, Vegetable.GRILLED_ONIONS));
        WhiteBunBurger b = new WhiteBunBurger("cDefault Burger", sauces, meats, veggues);
        WhiteBunBurger b2 = new WhiteBunBurger("bDefault Burger2", sauces, meats, veggues);
        WhiteBunBurger b3 = new WhiteBunBurger("aDefault Burger3", sauces, meats, veggues);
        WhiteBunBurger b4 = new WhiteBunBurger("afDefault Burger4", sauces, meats2, veggues);
        List<Burger> burgers = new ArrayList<>(Arrays.asList(b, b2, b3, b4));

        model.initRecipe(burgers);

        

        BurgerStoreView view = new BurgerStoreView(model, primaryStage);
        BurgerStoreController controller = new BurgerStoreController(model, view);
        view.setController(controller);

        Scene scene = new Scene(view.asParent(), 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
