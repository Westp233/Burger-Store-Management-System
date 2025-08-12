import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BurgerStoreView {
    private VBox view;
    private Label welcomLabel;
    private Button startBtn;
    private Button staffLoginBtn;

    private BurgerStoreController controller;
    private BurgerStoreModel model;
    private Stage primaryStage;

    public BurgerStoreView(BurgerStoreController controller, BurgerStoreModel model, Stage primaryStage) {
        this.controller = controller;
        this.model = model;
        this.primaryStage = primaryStage;

        createAndConfigurePane();
        createAndLayoutControls();
        updateControllerFromListeners();
        observeModelAndUpdateControls();
    }

    public Parent asParent() {
        return view;
    }

    private void observeModelAndUpdateControls() {

    }

    private void updateControllerFromListeners() {

    }

    private void createAndLayoutControls() { // The Hompage of the System
        welcomLabel = new Label("Welcome to Rim Burger!!!");

        startBtn = new Button("Start Ordering");
        startBtn.setOnAction(event -> createOrderMenu());

        staffLoginBtn = new Button("Staff Login");
        startBtn.setOnAction(event -> createLoginMenu());

        view.getChildren().addAll(welcomLabel, startBtn, staffLoginBtn);
    }

    private void createAndConfigurePane() {
        view = new VBox(5);
        view.setAlignment(Pos.CENTER);
    }

    private void createOrderMenu() {

    }

    private void createLoginMenu() {

    }
}