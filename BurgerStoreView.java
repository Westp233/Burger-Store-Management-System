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
        view.getChildren().clear();

        welcomLabel = new Label("Welcome to Rim Burger!!!");

        startBtn = new Button("Start Ordering");
        startBtn.setOnAction(event -> createOrderMenu());

        staffLoginBtn = new Button("Staff Login");
        staffLoginBtn.setOnAction(event -> createLoginMenu());

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
        idRow.setAlignment(Pos.CENTER);

        TextField passWordField = new TextField();
        HBox passWordRow = new HBox(5, new Label("Password: "), passWordField);
        passWordRow.setAlignment(Pos.CENTER);

        loginBtn = new Button("Login");
        loginBtn.setOnAction(event -> {
            try { // An catcher in case the user input a string at idField
                Staff staff = StaffManagement.login(Integer.parseInt(idField.getText().trim()),
                        passWordField.getText().trim());
                if (!(staff == null)) {
                    if (staff.getManagerPermission()) { // Check if the staff is manager to decide which menu we open
                        createManagerMenu(staff);
                        stage.close();
                    } else {
                        createStaffMenu(staff);
                        stage.close();
                    }
                } else {
                    createErrorPage();// If the information provided is invalid, show a login failed window
                }
            } catch (NumberFormatException e) {//Ask Tutor: Can we use this??????????????
                createErrorPage();
            }
        });

        closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(10, loginBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, idRow, passWordRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene loginScene = new Scene(root, 300, 150);

        stage.setScene(loginScene);
        stage.show();
    }

    private void createManagerMenu(Staff staff) { //This is the menu for the manager
        view.getChildren().clear();

        Label welcomeMessage = new Label("Welcome Back!! " + staff.getName());

        Label breakLabel = new Label("");

        Label message = new Label("Please Select a Option");

        Button modRecipeBtn = new Button("Modify Current Recipe");
        modRecipeBtn.setOnAction(event -> createModRecipePage());

        Button modIngredientBtn = new Button("Modify Current Ingredients' Price");
        modIngredientBtn.setOnAction(event -> createModIngredientPage());

        Button manageStaffBtn = new Button("Manage Current Staff");
        manageStaffBtn.setOnAction(event -> createManageStaffPage());

        Button returnHomePg = new Button("Log Out and Return to Homepage");
        returnHomePg.setOnAction(event -> {
            createAndLayoutControls();
        });

        view.getChildren().addAll(welcomeMessage, breakLabel, message, modRecipeBtn, modIngredientBtn, manageStaffBtn, returnHomePg);
        view.setAlignment(Pos.CENTER);
    }

    private void createStaffMenu(Staff staff) {
        view.getChildren().clear();

        Label welcomeMessage = new Label("Welcome Back!! " + staff.getName());

        Label breakLabel = new Label("");

        Label message = new Label("Please Select a Option");

        Button modRecipeBtn = new Button("Modify Current Recipe");
        modRecipeBtn.setOnAction(event -> createModRecipePage());

        Button modPersonalInfo = new Button("Modify Personal Information");
        modPersonalInfo.setOnAction(event -> createModPersonalInfoPage());

        Button returnHomePg = new Button("Log Out and Return to Homepage");
        returnHomePg.setOnAction(event -> {
            createAndLayoutControls();
        });

        view.getChildren().addAll(welcomeMessage, breakLabel, message, modRecipeBtn, modPersonalInfo, returnHomePg);
        view.setAlignment(Pos.CENTER);
    }

    private void createErrorPage() { // This is the pop-up window to the user if the login is failed
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label errorMessage = new Label("Login Failed, Please Check Your ID and Password");

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        VBox root = new VBox(5, errorMessage, closeBtn);
        root.setAlignment(Pos.CENTER);

        Scene errorScene = new Scene(root, 300, 150);

        stage.setScene(errorScene);
        stage.show();
    }

    private void createModRecipePage() {

    }

    private void createModIngredientPage() {

    }

    private void createManageStaffPage() {

    }

    private void createModPersonalInfoPage() {

    }
}