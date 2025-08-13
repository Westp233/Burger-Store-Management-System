import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BurgerStoreView {
    private VBox view;
    private Label welcomLabel;
    private Button startBtn;
    private Button staffLoginBtn;
    private Button loginBtn;
    private Button closeBtn;

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

    private void createLoginMenu() {// This is the login window for the staff
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField idField = new TextField();
        HBox idRow = new HBox(5, new Label("StaffID: "), idField);

        TextField passWordField = new TextField();
        HBox passWordRow = new HBox(5, new Label("Password: "), passWordField);

        loginBtn = new Button("Login");
        // Staff staff = model.staffLogin(idField.getText().trim(), passWordField.getText().trim());
        // loginBtn.setOnAction(event -> {
        //     if (!staff == null) {
        //         if (staff.getManagerPermission()) {
        //             createManagerMenu();
        //         } else {
        //             createStaffMenu();
        //         }
        //     } else {
        //         createErrorPage();
        //     }
        // });

        closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(10, loginBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, idRow, passWordRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene loginScene = new Scene(root, 400, 200);

        stage.setScene(loginScene);
        stage.show();
    }

    private void createManagerMenu() {

    }

    private void createStaffMenu() {

    }

    private void createErrorPage() {

    }
}