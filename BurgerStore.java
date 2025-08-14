import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BurgerStore extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rim Burger");
        BurgerStoreModel model = new BurgerStoreModel();
        BurgerStoreController controller = new BurgerStoreController(model);
        BurgerStoreView view = new BurgerStoreView(controller, model, primaryStage);

        Scene scene = new Scene(view.asParent(), 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Staff john = new Manager("password", "1234567", "John");
        StaffManagement.staffMap.put(john.getStaffID(), john);
        Staff carl = new Staff("password", "1234567", "Carl");
        StaffManagement.staffMap.put(carl.getStaffID(), carl);
        launch(args);
    }
}
