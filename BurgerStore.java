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
        List<Vegetable> veggues = new ArrayList<>(Arrays.asList(Vegetable.GREEN_PEPPERS, Vegetable.GRILLED_MUSHROOMS, Vegetable.GRILLED_ONIONS));
        WhiteBunBurger b = new WhiteBunBurger("Default Burger", sauces, meats, veggues);
        WhiteBunBurger b2 = new WhiteBunBurger("Default Burger2", sauces, meats, veggues);
        WhiteBunBurger b3 = new WhiteBunBurger("Default Burger3", sauces, meats, veggues);
        List<Burger> burgers = new ArrayList<>(Arrays.asList(b, b2, b3));

        model.initRecipe(burgers);

        

        BurgerStoreView view = new BurgerStoreView(model, primaryStage);
        BurgerStoreController controller = new BurgerStoreController(model, view);
        view.setController(controller);

        Scene scene = new Scene(view.asParent(), 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
