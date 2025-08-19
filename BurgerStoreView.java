import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Questions need to be clear:
//What exactly what we need to do with the previous assignment
//Shoule we just put it there and do nothing?
//Or we should revise the method inside it like getter and setter in to model and controller
//If not, am I able to use the getter and setter?
//Can I use the method In the previous assignment?
//Used something didn't taught in class, line108, line267

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
                if (!(staff == null)) {// Shoule we use get here????????
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

    private void createStaffMenu(Staff staff) { //This is the menu for the normal staff
        view.getChildren().clear();

        Label welcomeMessage = new Label("Welcome Back!! " + staff.getName());

        Label breakLabel = new Label("");

        Label message = new Label("Please Select a Option");

        Button modRecipeBtn = new Button("Modify Current Recipe");
        modRecipeBtn.setOnAction(event -> createModRecipePage());

        Button modPersonalInfo = new Button("Modify Personal Information");
        modPersonalInfo.setOnAction(event -> createModPersonalInfoPage(staff));

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

    private void createModRecipePage() { //this is a table view for checking the recipe list and deleting them
        Stage stage = new Stage();
        stage.initOwner(primaryStage);

        TableView<Burger> table = new TableView<>();

        TableColumn<Burger, String> nameCol = new TableColumn<>("Name");
        TableColumn<Burger, String> ingredientCol = new TableColumn<>("Ingredients");
        TableColumn<Burger, Integer> idCol = new TableColumn<>("ID");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ingredientCol.setCellValueFactory(cellData -> cellData.getValue().ingredientProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        table.getColumns().addAll(idCol, nameCol, ingredientCol);
        table.setItems(model.burgerProperty());

        Button removeBtn = new Button("Remove");
        removeBtn.setOnAction(event -> {
            int index = table.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                this.controller.removeRecipe(index);
            }
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox buttonRow = new HBox(5, removeBtn, closeBtn);

        VBox root = new VBox(5, table, buttonRow);

        Scene modRecipeScene = new Scene(root, 600, 300);

        stage.setScene(modRecipeScene);
        stage.show();
    }

    private void createModIngredientPage() {
        //Probably using a table view as well however ingredient are sparate into different enum, don't know if we can change the code from last assessment
    }

    private void createManageStaffPage() {  //This is the table view for checking all the staff's information and change them
        Stage stage = new Stage();
        stage.initOwner(primaryStage);

        TableView<Staff> table = new TableView<>();

        TableColumn<Staff, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Staff, String> nameCol = new TableColumn<>("Name");
        TableColumn<Staff, String> phoneNumCol = new TableColumn<>("Phone Number");
        TableColumn<Staff, Level> levelCol = new TableColumn<>("Acess Level"); //Another enum if thats fine

        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneNumCol.setCellValueFactory(cellData -> cellData.getValue().phoneNumProperty());
        levelCol.setCellFactory(cellData -> cellData.getValue().levelProperty());

        table.getColumns().addAll(idCol, nameCol, phoneNumCol, levelCol);
        table.setItems(model.staffProperty());

        Button addStaffBtn = new Button("Add"); //Add new staff
        addStaffBtn.setOnAction(event -> createAddStaffPage());

        Button editStaffBtn = new Button("Edit"); //Modify the staff's personal information
        editStaffBtn.setOnAction(event -> {
            int index = table.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                createEditStaffPage(table.getSelectionModel().getSelectedItem());
            }
        });

        Button fireStaffBtn = new Button("Fire"); //Button for fire a staff
        fireStaffBtn.setOnAction(event -> {
            int index = table.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                this.controller.removeStaff(index);
            }
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox buttonRow = new HBox(5, addStaffBtn, editStaffBtn, fireStaffBtn, closeBtn);

        VBox root = new VBox(5, table, buttonRow);

        Scene modRecipeScene = new Scene(root, 600, 300);

        stage.setScene(modRecipeScene);
        stage.show();
    }

    private void createModPersonalInfoPage(Staff s) {

    }

    private void createAddStaffPage() {

    }

    private void createEditStaffPage(Staff s) {

    }
}