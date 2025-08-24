import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

    public BurgerStoreView(BurgerStoreModel model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;

        createAndConfigurePane();
        createAndLayoutControls();
        updateControllerFromListeners();
        observeModelAndUpdateControls();
    }

    public void setController(BurgerStoreController controller) { // Our code requires the controller directly do
                                                                  // something with the view
        this.controller = controller;
    }

    public Parent asParent() {
        return view;
    }

    private void observeModelAndUpdateControls() {
        // Nothing here
    }

    private void updateControllerFromListeners() {
        // Nothing here
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

    private void createOrderMenu() { // this is the menu for the customer
        view.getChildren().clear();

        Label message = new Label("Please Choose an Option");

        Label breakLabel = new Label(" ");

        Button orderBtn = new Button("Ordering from Current Menu");
        orderBtn.setOnAction(event -> createOrderPage());

        Button customizeBtn = new Button("Customize Your Own Burger Recipe");
        customizeBtn.setOnAction(event -> createCustomizePage());

        Button returnBtn = new Button("Return to the Home Page");
        returnBtn.setOnAction(event -> createAndLayoutControls());

        view.getChildren().addAll(message, breakLabel, orderBtn, customizeBtn, returnBtn);
        view.setAlignment(Pos.CENTER);
    }

    private void createOrderPage() { // This is the window for ordering from the menu
        view.getChildren().clear();

        TableView<Burger> table = new TableView<>();

        TableColumn<Burger, String> nameCol = new TableColumn<>("Name");
        TableColumn<Burger, String> ingredientCol = new TableColumn<>("Ingredients");
        TableColumn<Burger, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Burger, Double> priceCol = new TableColumn<>("Price");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ingredientCol.setCellValueFactory(cellData -> cellData.getValue().ingredientProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().burgerIDProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        table.getColumns().addAll(idCol, nameCol, ingredientCol, priceCol);
        table.setItems(model.burgersProperty());

        Button returnBtn = new Button("Return to Previous Step");
        returnBtn.setOnAction(event -> createOrderMenu());

        Button sortByIDBtn = new Button("Sort by ID");
        sortByIDBtn.setOnAction(event -> controller.sortID());

        Button sortByNameBtn = new Button("Sort by Name");
        sortByNameBtn.setOnAction(event -> controller.sortName());

        Button sortByPriceA = new Button("Sort by Price (Ascending)");
        sortByPriceA.setOnAction(event -> controller.sortPrice());

        Button sortByPriceD = new Button("Sort by Price (Descending)");
        sortByPriceD.setOnAction(event -> controller.sortPriceR());

        Button makeComboBtn = new Button("Make it a Combo");
        makeComboBtn.setOnAction(event -> {
            int index = table.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                createMakeComboPage(table.getSelectionModel().getSelectedItem());
            }
        });

        Button proceedBtn = new Button("Proceed with Burger Only");
        proceedBtn.setOnAction(event -> {
            int index = table.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                createPaymentPage(table.getSelectionModel().getSelectedItem());
            }
        });

        HBox sortBtnRow = new HBox(5, sortByIDBtn, sortByNameBtn, sortByPriceA, sortByPriceD);

        HBox functionBtnRow = new HBox(5, returnBtn, makeComboBtn, proceedBtn);

        view.getChildren().addAll(table, sortBtnRow, functionBtnRow);
        view.setAlignment(Pos.CENTER);
    }

    private void createCustomizePage() { // This is the window for customize the burger
        view.getChildren().clear();

        Label sauceLabel = new Label("Sauces: ");

        TextField mayoField = new TextField("0");
        HBox mayoRow = new HBox(5, new Label("Mayo: "), mayoField);

        TextField kecthupField = new TextField("0");
        HBox ketchupRow = new HBox(5, new Label("Ketchup: "), kecthupField);

        TextField mustarField = new TextField("0");
        HBox mustardRow = new HBox(5, new Label("Mustard: "), mustarField);

        TextField relishField = new TextField("0");
        HBox relishRow = new HBox(5, new Label("Relish: "), relishField);

        TextField bbqField = new TextField("0");
        HBox bbqRow = new HBox(5, new Label("BBQ Sauce: "), bbqField);

        TextField steakField = new TextField("0");
        HBox steakRow = new HBox(5, new Label("Steak Sauce: "), steakField);

        TextField hotField = new TextField("0");
        HBox hotRow = new HBox(5, new Label("Hot Sauce: "), hotField);

        VBox sauce = new VBox(5, sauceLabel, mayoRow, ketchupRow, mustardRow, relishRow, bbqRow, steakRow, hotRow);

        Label vegetableLabel = new Label("Vegetables: ");

        TextField lettuceField = new TextField("0");
        HBox lettuceRow = new HBox(5, new Label("Lettuce: "), lettuceField);

        TextField tomatoField = new TextField("0");
        HBox tomatoRow = new HBox(5, new Label("Tomato: "), tomatoField);

        TextField onionField = new TextField("0");
        HBox onionRow = new HBox(5, new Label("Onions: "), onionField);

        TextField grilledOnionField = new TextField("0");
        HBox grilledOnionRow = new HBox(5, new Label("Grilled Onions: "), grilledOnionField);

        TextField grilledMushroomField = new TextField("0");
        HBox grilledMushroomRow = new HBox(5, new Label("Grilled Mushrooms: "), grilledMushroomField);

        TextField jalapenoField = new TextField("0");
        HBox jalapenoRow = new HBox(5, new Label("Jalapeno: "), jalapenoField);

        TextField greenPepperField = new TextField("0");
        HBox greenPepperRow = new HBox(5, new Label("Green Peppers: "), greenPepperField);

        TextField pickleField = new TextField("0");
        HBox pickleRow = new HBox(5, new Label("Pickles: "), pickleField);

        VBox vegetable = new VBox(5, vegetableLabel, lettuceRow, tomatoRow, onionRow, grilledOnionRow,
                grilledMushroomRow,
                jalapenoRow, greenPepperRow, pickleRow);

        Label meatLabel = new Label("Meats: ");

        TextField fishField = new TextField("0");
        HBox fishRow = new HBox(5, new Label("Fish: "), fishField);

        TextField chickenField = new TextField("0");
        HBox chickenRow = new HBox(5, new Label("Chicken: "), chickenField);

        TextField beefField = new TextField("0");
        HBox beefRow = new HBox(5, new Label("Beef: "), beefField);

        TextField baconField = new TextField("0");
        HBox baconRow = new HBox(5, new Label("Bacon: "), baconField);

        VBox meat = new VBox(5, meatLabel, fishRow, chickenRow, beefRow, baconRow);

        HBox ingredientRow = new HBox(10, sauce, meat, vegetable);
        ingredientRow.setAlignment(Pos.CENTER);

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton whiteBtn = new RadioButton("White Bun");
        whiteBtn.setToggleGroup(toggleGroup);

        RadioButton seasameBtn = new RadioButton("Seasame Bun");
        seasameBtn.setToggleGroup(toggleGroup);

        RadioButton glutenFreeBtn = new RadioButton("Gulten Free Bun");
        glutenFreeBtn.setToggleGroup(toggleGroup);

        Button returnBtn = new Button("Return to Previous Step");
        returnBtn.setOnAction(event -> createOrderMenu());

        Button confirmButton = new Button("Confirm Recipe");
        confirmButton.setOnAction(event -> {
            String bun = "";
            if (whiteBtn.isSelected()) {
                bun = "White Bun";
            } else if (seasameBtn.isSelected()) {
                bun = "Seasame Bun";
            } else {
                bun = "Gulten Free Bun";
            }

            String fish = fishField.getText();
            String chicken = chickenField.getText();
            String beef = beefField.getText();
            String bacon = baconField.getText();

            String lettuce = lettuceField.getText();
            String tomato = tomatoField.getText();
            String onion = onionField.getText();
            String grilledOnion = grilledOnionField.getText();
            String grilledMushroom = grilledMushroomField.getText();
            String jalapeno = jalapenoField.getText();
            String greenPepper = greenPepperField.getText();
            String pickle = pickleField.getText();

            String mayo = mayoField.getText();
            String ketchup = kecthupField.getText();
            String mustard = mustarField.getText();
            String relish = relishField.getText();
            String bbq = bbqField.getText();
            String steak = steakField.getText();
            String hot = hotField.getText();

            controller.createBurger(bun,
                    fish, chicken, beef, bacon,
                    lettuce, tomato, onion, grilledOnion, grilledMushroom, jalapeno, greenPepper, pickle,
                    mayo, ketchup, mustard, relish, bbq, steak, hot);
        });

        HBox bunRow = new HBox(10, whiteBtn, seasameBtn, glutenFreeBtn);
        bunRow.setAlignment(Pos.CENTER);

        HBox btnRow = new HBox(10, returnBtn, confirmButton);
        btnRow.setAlignment(Pos.CENTER);

        view.getChildren().addAll(ingredientRow, bunRow, btnRow);
    }

    public void createFinalizationPage(Burger b) { // this window is the confirm page for the customize burger
        view.getChildren().clear();

        Label message = new Label("Your Customize Burger: ");

        Label breakLabel = new Label("");

        Label burgerDetail = new Label(b.toString());

        Button returnBtn = new Button("Return to Previous Step");
        returnBtn.setOnAction(event -> {
            controller.removeRecentRecipe();
            createCustomizePage();
        });

        Button uploadWithBurgerBtn = new Button("Proceed with Burger Only (Upload this Recipe)");
        uploadWithBurgerBtn.setOnAction(event -> {
            createNamingPageForBurger(b);
        });

        Button uploadWithComboBtn = new Button("Make it a Combo (Upload this Recipe)");
        uploadWithComboBtn.setOnAction(event -> {
            createNamingPageForCombo(b);

        });

        Button burgerBtn = new Button("Proceed with Burger Only (Don't upload this Recipe)");
        burgerBtn.setOnAction(event -> {
            controller.removeRecentRecipe();
            createPaymentPage(b);
        });

        Button comboBtn = new Button("Make it a Combo (Upload this Recipe)");
        comboBtn.setOnAction(event -> {
            controller.removeRecentRecipe();
            createMakeComboPage(b);
        });

        HBox uploadRow = new HBox(5, uploadWithBurgerBtn, uploadWithComboBtn);
        uploadRow.setAlignment(Pos.CENTER);

        HBox noUploadRow = new HBox(5, burgerBtn, comboBtn, returnBtn);
        noUploadRow.setAlignment(Pos.CENTER);

        view.getChildren().addAll(message, breakLabel, burgerDetail, uploadRow, noUploadRow);
    }

    private void createNamingPageForBurger(Burger b) { // name the recipe and upload it to the menu, then proceed to
                                                       // payment
        Stage stage = new Stage();
        stage.setTitle("Name the Recipe");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField nameField = new TextField();

        HBox nameRow = new HBox(5, new Label("Name: "), nameField);
        nameRow.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(event -> {
            controller.setBurgerName(b, nameField.getText());
            controller.uploadRecipe(b);
            createPaymentPage(b);
            stage.close();
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, submitBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, nameRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene namingScene = new Scene(root, 300, 150);

        stage.setScene(namingScene);
        stage.show();

    }

    private void createNamingPageForCombo(Burger b) { // name the recipe and upload it to the menu, then proceed to make
                                                      // it a combo
        Stage stage = new Stage();
        stage.setTitle("Name the Recipe");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField nameField = new TextField();

        HBox nameRow = new HBox(5, new Label("Name: "), nameField);
        nameRow.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(event -> {
            controller.setBurgerName(b, nameField.getText());
            controller.uploadRecipe(b);
            createMakeComboPage(b);
            stage.close();
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, submitBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, nameRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene namingScene = new Scene(root, 300, 150);

        stage.setScene(namingScene);
        stage.show();

    }

    private void createMakeComboPage(Burger b) { // This page allows the user to select the sides and drinks for their
                                                 // combo
        view.getChildren().clear();

        Label sideLabel = new Label("Side: ");

        ToggleGroup sideGroup = new ToggleGroup();

        RadioButton friesBtn = new RadioButton("Fries");
        friesBtn.setToggleGroup(sideGroup);

        RadioButton onionRingsBtn = new RadioButton("Onion Rings");
        onionRingsBtn.setToggleGroup(sideGroup);

        RadioButton chickenNuggetsBtn = new RadioButton("Chicken Nuggets");
        chickenNuggetsBtn.setToggleGroup(sideGroup);

        RadioButton chickenWingsBtn = new RadioButton("Chicken Wings");
        chickenWingsBtn.setToggleGroup(sideGroup);

        RadioButton saladBtn = new RadioButton("Salad");
        saladBtn.setToggleGroup(sideGroup);

        VBox sideRow = new VBox(5, sideLabel, friesBtn, onionRingsBtn, chickenNuggetsBtn, chickenWingsBtn, saladBtn);

        Label drinkLabel = new Label("Drink: ");

        ToggleGroup drinkGroup = new ToggleGroup();

        RadioButton cokeBtn = new RadioButton("Coke");
        cokeBtn.setToggleGroup(drinkGroup);

        RadioButton cokeZeroBtn = new RadioButton("Coke Zero");
        cokeZeroBtn.setToggleGroup(drinkGroup);

        RadioButton fantaBtn = new RadioButton("Fanta");
        fantaBtn.setToggleGroup(drinkGroup);

        RadioButton spriteBtn = new RadioButton("Sprite");
        spriteBtn.setToggleGroup(drinkGroup);

        RadioButton orangeJuiceBtn = new RadioButton("Orange Juice");
        orangeJuiceBtn.setToggleGroup(drinkGroup);

        RadioButton milkShakeBtn = new RadioButton("Milk Shake");
        milkShakeBtn.setToggleGroup(drinkGroup);

        Button proceedBtn = new Button("Proceed");
        proceedBtn.setOnAction(event -> {
            Side s = null;
            Drink d = null;

            if (friesBtn.isSelected()) {
                s = Side.FRIES;
            } else if (onionRingsBtn.isSelected()) {
                s = Side.ONION_RINGS;
            } else if (chickenNuggetsBtn.isSelected()) {
                s = Side.CHICKEN_NUGGETS;
            } else if (chickenWingsBtn.isSelected()) {
                s = Side.CHICKEN_WINGS;
            } else {
                s = Side.SALAD;
            }

            if (cokeBtn.isSelected()) {
                d = Drink.COKE;
            } else if (cokeZeroBtn.isSelected()) {
                d = Drink.COKO_ZERO;
            } else if (fantaBtn.isSelected()) {
                d = Drink.FANTA;
            } else if (spriteBtn.isSelected()) {
                d = Drink.SPRITE;
            } else if (orangeJuiceBtn.isSelected()) {
                d = Drink.ORANGE_JUICE;
            } else {
                d = Drink.MILK_SHAKE;
            }

            controller.createCombo(b, s, d);
        });

        VBox drinkRow = new VBox(5, drinkLabel, cokeBtn, cokeZeroBtn, fantaBtn, spriteBtn, orangeJuiceBtn,
                milkShakeBtn);

        HBox selectiveRow = new HBox(10, sideRow, drinkRow);
        selectiveRow.setAlignment(Pos.CENTER);

        view.getChildren().addAll(selectiveRow, proceedBtn);
    }

    private void createPaymentPage(Burger b) {  //final payment window if user just select a burger
        view.getChildren().clear();

        Label burgerMessage = new Label("Your Burger: ");

        Label burgerDetail = new Label(b.toString());

        Label price = new Label("Final Price: $" + b.countPrice());

        Label breakLabel = new Label("");

        Label breakLabel2 = new Label("");

        Label message = new Label("Please Choose a Payment Method");

        Button cardBtn = new Button("Credit Card");
        cardBtn.setOnAction(event -> createCardPaymentPage());

        Button frontBtn = new Button("Pay at the Front");
        frontBtn.setOnAction(event -> createPayAtFrontPage());

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> createAndLayoutControls());

        view.getChildren().addAll(burgerMessage, burgerDetail, breakLabel, price, breakLabel2, message, cardBtn, frontBtn, cancelBtn);
    }

    public void createPaymentPage(Combo c) {
        view.getChildren().clear();

        Label burgerMessage = new Label("Your Burger: ");

        Label burgerDetail = new Label(c.getBurger().toString());

        Label comboMessage = new Label("Your Combo's Detail: ");

        Label drinkDetail = new Label("Drink: " + c.getDrink().getName());

        Label sideDetail = new Label("Side: " + c.getSide().getName());

        Label price = new Label("Final Price: $" + c.getPrice());

        Label breakLabel = new Label("");

        Label breakLabel2 = new Label("");

        Label breakLabel3 = new Label("");

        Label message = new Label("Please Choose a Payment Method");

        Button cardBtn = new Button("Credit Card");
        cardBtn.setOnAction(event -> createCardPaymentPage());

        Button frontBtn = new Button("Pay at the Front");
        frontBtn.setOnAction(event -> createPayAtFrontPage());

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> createAndLayoutControls());

        view.getChildren().addAll(burgerMessage, burgerDetail, breakLabel, comboMessage, drinkDetail, sideDetail, breakLabel2, price, breakLabel3, message, cardBtn, frontBtn, cancelBtn);
    }

    private void createCardPaymentPage() {
        view.getChildren().clear();

        Label message = new Label("Payment Successful! Enjoy your meal");

        Button endBtn = new Button("End");
        endBtn.setOnAction(event -> createAndLayoutControls());

        view.getChildren().addAll(message, endBtn);
    }

    private void createPayAtFrontPage() {
        view.getChildren().clear();

        Label message = new Label("Please Go to the Front to Finish the Payment");

        Button endBtn = new Button("End");
        endBtn.setOnAction(event -> createAndLayoutControls());

        view.getChildren().addAll(message, endBtn);
    }

    private void createLoginMenu() {// This is the login window for the staff
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Login");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField idField = new TextField();
        HBox idRow = new HBox(5, new Label("StaffID: "), idField);
        idRow.setAlignment(Pos.CENTER);

        TextField passWordField = new TextField();
        HBox passWordRow = new HBox(5, new Label("Password: "), passWordField);
        passWordRow.setAlignment(Pos.CENTER);

        loginBtn = new Button("Login");
        loginBtn.setOnAction(event -> {
            controller.login(idField.getText(), passWordField.getText());
            stage.close();
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

    public void createManagerMenu(Staff staff) { // This is the menu for the manager
        view.getChildren().clear();

        Label welcomeMessage = new Label("Welcome Back!! " + staff.getName());

        Label breakLabel = new Label("");

        Label message = new Label("Please Select a Option");

        Button modRecipeBtn = new Button("Modify Current Recipe");
        modRecipeBtn.setOnAction(event -> createModRecipePage());

        Button modIngredientBtn = new Button("Modify Current Ingredients' Price");
        modIngredientBtn.setOnAction(event -> createModIngredientSelectionPage());

        Button manageStaffBtn = new Button("Manage Current Staff");
        manageStaffBtn.setOnAction(event -> createManageStaffPage());

        Button returnHomeBtn = new Button("Log Out and Return to Homepage");
        returnHomeBtn.setOnAction(event -> {
            createAndLayoutControls();
        });

        view.getChildren().addAll(welcomeMessage, breakLabel, message, modRecipeBtn, modIngredientBtn, manageStaffBtn,
                returnHomeBtn);
        view.setAlignment(Pos.CENTER);
    }

    public void createStaffMenu(Staff staff) { // This is the menu for the normal staff
        view.getChildren().clear();

        Label welcomeMessage = new Label("Welcome Back!! " + staff.getName());

        Label breakLabel = new Label("");

        Label message = new Label("Please Select a Option");

        Button modRecipeBtn = new Button("Modify Current Recipe");
        modRecipeBtn.setOnAction(event -> createModRecipePage());

        Button modPersonalInfo = new Button("Modify Personal Information");
        modPersonalInfo.setOnAction(event -> createModPersonalInfoPage(staff));

        Button returnHomePg = new Button("Log Out and Return to Homepage");
        returnHomePg.setOnAction(event -> createAndLayoutControls());

        view.getChildren().addAll(welcomeMessage, breakLabel, message, modRecipeBtn, modPersonalInfo, returnHomePg);
        view.setAlignment(Pos.CENTER);
    }

    private void createModRecipePage() { // this is a table view for checking the recipe list and deleting them
        Stage stage = new Stage();
        stage.setTitle("Modifying Recipe");
        stage.initOwner(primaryStage);

        TableView<Burger> table = new TableView<>();

        TableColumn<Burger, String> nameCol = new TableColumn<>("Name");
        TableColumn<Burger, String> ingredientCol = new TableColumn<>("Ingredients");
        TableColumn<Burger, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Burger, Double> priceCol = new TableColumn<>("Price");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ingredientCol.setCellValueFactory(cellData -> cellData.getValue().ingredientProperty());
        idCol.setCellValueFactory(cellData -> cellData.getValue().burgerIDProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        table.getColumns().addAll(idCol, nameCol, ingredientCol, priceCol);
        table.setItems(model.burgersProperty());

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

        Scene modRecipeScene = new Scene(root, 900, 700);

        stage.setScene(modRecipeScene);
        stage.show();
    }

    private void createModIngredientSelectionPage() { // this is the window let user choose which category of the
                                                      // ingredient to modify
        Stage stage = new Stage();
        stage.setTitle("Modifying Ingredients' Price");
        stage.initOwner(primaryStage);

        Label message = new Label("Please Select a Option:");

        Label breakLabel = new Label("");

        Button modMeatBtn = new Button("Modify Current Meat Price");
        modMeatBtn.setOnAction(event -> createModMeatPage());

        Button modSauceBtn = new Button("Modify Current Sauce Price");
        modSauceBtn.setOnAction(event -> createModSaucePage());

        Button modVegetableBtn = new Button("Modify Current Vegetable Price");
        modVegetableBtn.setOnAction(event -> createModVegetablePage());

        Button modDrinkBtn = new Button("Modify Current Drink Price");
        modDrinkBtn.setOnAction(event -> createModDrinkPage());

        Button modSideBtn = new Button("Modify Current Side Price");
        modSideBtn.setOnAction(event -> createModSidePage());

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        VBox root = new VBox(5, message, breakLabel, modMeatBtn, modSauceBtn, modVegetableBtn, modDrinkBtn, modSideBtn,
                closeBtn);
        root.setAlignment(Pos.CENTER);

        Scene modIngredientSelectionScene = new Scene(root, 900, 700);

        stage.setScene(modIngredientSelectionScene);
        stage.show();
    }

    private void createModMeatPage() { // this is a listView to show all the Meat enum elements
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Modify Meat's Price");

        TableView<Meat> table = new TableView<>();

        TableColumn<Meat, String> nameCol = new TableColumn<>("Ingredient Name");
        TableColumn<Meat, Double> priceCol = new TableColumn<>("Price");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        table.getColumns().addAll(nameCol, priceCol);

        ObservableList<Meat> data = FXCollections.observableArrayList(Meat.values());

        table.setItems(data);

        Button changePriceBtn = new Button("Change Price");
        changePriceBtn.setOnAction(event -> {
            Meat meat = table.getSelectionModel().getSelectedItem();
            if (meat != null) {
                createChangePricePage(meat);
            }
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, changePriceBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, table, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene modMeatScene = new Scene(root, 900, 700);

        stage.setScene(modMeatScene);
        stage.show();
    }

    private void createModSaucePage() { // this is a listView to show all the Sauce enum elements
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Modify Sauce's Price");

        TableView<Sauce> table = new TableView<>();

        TableColumn<Sauce, String> nameCol = new TableColumn<>("Ingredient Name");
        TableColumn<Sauce, Double> priceCol = new TableColumn<>("Price");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        table.getColumns().addAll(nameCol, priceCol);

        ObservableList<Sauce> data = FXCollections.observableArrayList(Sauce.values());

        table.setItems(data);

        Button changePriceBtn = new Button("Change Price");
        changePriceBtn.setOnAction(event -> {
            Sauce sauce = table.getSelectionModel().getSelectedItem();
            if (sauce != null) {
                createChangePricePage(sauce);
            }
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, changePriceBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, table, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene modSauceScene = new Scene(root, 900, 700);

        stage.setScene(modSauceScene);
        stage.show();
    }

    private void createModVegetablePage() { // this is a listView to show all the Vegetable enum elements
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Modify Vegetable's Price");

        TableView<Vegetable> table = new TableView<>();

        TableColumn<Vegetable, String> nameCol = new TableColumn<>("Ingredient Name");
        TableColumn<Vegetable, Double> priceCol = new TableColumn<>("Price");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        table.getColumns().addAll(nameCol, priceCol);

        ObservableList<Vegetable> data = FXCollections.observableArrayList(Vegetable.values());

        table.setItems(data);

        Button changePriceBtn = new Button("Change Price");
        changePriceBtn.setOnAction(event -> {
            Vegetable vegetable = table.getSelectionModel().getSelectedItem();
            if (vegetable != null) {
                createChangePricePage(vegetable);
            }
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, changePriceBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, table, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene modVegetableScene = new Scene(root, 900, 700);

        stage.setScene(modVegetableScene);
        stage.show();
    }

    private void createModDrinkPage() { // this is a listView to show all the Drink enum elements
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Modify Drink's Price");

        TableView<Drink> table = new TableView<>();

        TableColumn<Drink, String> nameCol = new TableColumn<>("Ingredient Name");
        TableColumn<Drink, Double> priceCol = new TableColumn<>("Price");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        table.getColumns().addAll(nameCol, priceCol);

        ObservableList<Drink> data = FXCollections.observableArrayList(Drink.values());

        table.setItems(data);

        Button changePriceBtn = new Button("Change Price");
        changePriceBtn.setOnAction(event -> {
            Drink drink = table.getSelectionModel().getSelectedItem();
            if (drink != null) {
                createChangePricePage(drink);
            }
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, changePriceBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, table, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene modDrinkScene = new Scene(root, 900, 700);

        stage.setScene(modDrinkScene);
        stage.show();
    }

    private void createModSidePage() { // this is a listView to show all the Side enum elements
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Modify Side's Price");

        TableView<Side> table = new TableView<>();

        TableColumn<Side, String> nameCol = new TableColumn<>("Ingredient Name");
        TableColumn<Side, Double> priceCol = new TableColumn<>("Price");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        table.getColumns().addAll(nameCol, priceCol);

        ObservableList<Side> data = FXCollections.observableArrayList(Side.values());

        table.setItems(data);

        Button changePriceBtn = new Button("Change Price");
        changePriceBtn.setOnAction(event -> {
            Side side = table.getSelectionModel().getSelectedItem();
            if (side != null) {
                createChangePricePage(side);
            }
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, changePriceBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, table, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene modVegetableScene = new Scene(root, 900, 700);

        stage.setScene(modVegetableScene);
        stage.show();
    }

    private void createChangePricePage(Meat meat) { // the window for modiying the price for a meat
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Change Price");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField priceField = new TextField();
        HBox priceRow = new HBox(5, new Label("New Price"), priceField);
        priceRow.setAlignment(Pos.CENTER);

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            controller.setPrice(meat, priceField.getText());
            stage.close();
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, saveBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, priceRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene changePriceScene = new Scene(root, 300, 150);
        stage.setScene(changePriceScene);
        stage.show();
    }

    private void createChangePricePage(Sauce sauce) { // the window for modiying the price for a sauce
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Change Price");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField priceField = new TextField();
        HBox priceRow = new HBox(5, new Label("New Price"), priceField);
        priceRow.setAlignment(Pos.CENTER);

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            controller.setPrice(sauce, priceField.getText());
            stage.close();
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, saveBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, priceRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene changePriceScene = new Scene(root, 300, 150);
        stage.setScene(changePriceScene);
        stage.show();
    }

    private void createChangePricePage(Vegetable vegetable) { // the window for modiying the price for a vegetable
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Change Price");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField priceField = new TextField();
        HBox priceRow = new HBox(5, new Label("New Price"), priceField);
        priceRow.setAlignment(Pos.CENTER);

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            controller.setPrice(vegetable, priceField.getText());
            stage.close();
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, saveBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, priceRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene changePriceScene = new Scene(root, 300, 150);
        stage.setScene(changePriceScene);
        stage.show();
    }

    private void createChangePricePage(Drink drink) { // the window for modiying the price for a drink
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Change Price");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField priceField = new TextField();
        HBox priceRow = new HBox(5, new Label("New Price"), priceField);
        priceRow.setAlignment(Pos.CENTER);

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            controller.setPrice(drink, priceField.getText());
            stage.close();
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, saveBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, priceRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene changePriceScene = new Scene(root, 300, 150);
        stage.setScene(changePriceScene);
        stage.show();
    }

    private void createChangePricePage(Side side) { // the window for modiying the price for a side
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Change Price");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField priceField = new TextField();
        HBox priceRow = new HBox(5, new Label("New Price"), priceField);
        priceRow.setAlignment(Pos.CENTER);

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            controller.setPrice(side, priceField.getText());
            stage.close();
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, saveBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, priceRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene changePriceScene = new Scene(root, 300, 150);
        stage.setScene(changePriceScene);
        stage.show();
    }

    private void createManageStaffPage() { // This is the table view for checking all the staff's information
        Stage stage = new Stage();
        stage.setTitle("Staff Management");
        stage.initOwner(primaryStage);

        TableView<Staff> table = new TableView<>();

        TableColumn<Staff, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Staff, String> nameCol = new TableColumn<>("Name");
        TableColumn<Staff, String> phoneNumCol = new TableColumn<>("Phone Number");
        TableColumn<Staff, String> levelCol = new TableColumn<>("Acess Level");

        idCol.setCellValueFactory(cellData -> cellData.getValue().staffIDProperty().asObject());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneNumCol.setCellValueFactory(cellData -> cellData.getValue().phoneNumProperty());
        levelCol.setCellValueFactory(cellData -> cellData.getValue().permissionProperty());

        table.getColumns().addAll(idCol, nameCol, phoneNumCol, levelCol);
        table.setItems(model.staffsProperty());

        Button addStaffBtn = new Button("Add"); // Add new staff
        addStaffBtn.setOnAction(event -> createAddStaffPage());

        Button editStaffBtn = new Button("Edit"); // Modify the staff's personal information
        editStaffBtn.setOnAction(event -> {
            int index = table.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                createEditStaffPage(table.getSelectionModel().getSelectedItem());
            }
        });

        Button fireStaffBtn = new Button("Fire"); // Button for fire a staff
        fireStaffBtn.setOnAction(event -> {
            int index = table.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                this.controller.removeStaff(index);
            }
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox buttonRow = new HBox(5, addStaffBtn, editStaffBtn, fireStaffBtn,
                closeBtn);

        VBox root = new VBox(5, table, buttonRow);

        Scene modRecipeScene = new Scene(root, 900, 700);

        stage.setScene(modRecipeScene);
        stage.show();
    }

    private void createModPersonalInfoPage(Staff s) { // Staff method, only editing personal informtaion
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Personal Information");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField nameField = new TextField(s.getName());
        HBox nameRow = new HBox(5, new Label("Name: "), nameField);
        nameRow.setAlignment(Pos.CENTER);

        TextField phoneNumField = new TextField(s.getPhoneNum());
        HBox phonNumRow = new HBox(5, new Label("Phone Number"), phoneNumField);
        phonNumRow.setAlignment(Pos.CENTER);

        TextField passworField = new TextField(s.getPassword());
        HBox passwordRow = new HBox(5, new Label("Password: "), passworField);
        passwordRow.setAlignment(Pos.CENTER);

        TextField confirmationField = new TextField(s.getPassword());
        HBox confirmationRow = new HBox(5, new Label("Confirmation: "), confirmationField);
        confirmationRow.setAlignment(Pos.CENTER);

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            controller.editStaff(s, nameField.getText(), phoneNumField.getText(), passworField.getText(),
                    confirmationField.getText(), "Staff");
            stage.close();
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, saveBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, nameRow, phonNumRow, passwordRow, confirmationRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene editStaffScene = new Scene(root, 900, 700);

        stage.setScene(editStaffScene);
        stage.show();
    }

    private void createAddStaffPage() { // window for adding a new staff
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Add New Staff");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField nameField = new TextField();
        HBox nameRow = new HBox(5, new Label("Name: "), nameField);
        nameRow.setAlignment(Pos.CENTER);

        TextField phoneNumField = new TextField();
        HBox phonNumRow = new HBox(5, new Label("Phone Number"), phoneNumField);
        phonNumRow.setAlignment(Pos.CENTER);

        TextField passworField = new TextField();
        HBox passwordRow = new HBox(5, new Label("Password: "), passworField);
        passwordRow.setAlignment(Pos.CENTER);

        TextField confirmationField = new TextField();
        HBox confirmationRow = new HBox(5, new Label("Confirmation: "), confirmationField);
        confirmationRow.setAlignment(Pos.CENTER);

        Button createBtn = new Button("Create");
        createBtn.setOnAction(event -> {
            controller.createStaff(nameField.getText(), phoneNumField.getText(), passworField.getText(),
                    confirmationField.getText());
            stage.close();
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, createBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, nameRow, phonNumRow, passwordRow, confirmationRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene addStaffScene = new Scene(root, 900, 700);

        stage.setScene(addStaffScene);
        stage.show();
    }

    private void createEditStaffPage(Staff s) { // Manager-only method, edit information for a selected staff
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.setTitle("Edit Staff");
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField nameField = new TextField(s.getName());
        HBox nameRow = new HBox(5, new Label("Name: "), nameField);
        nameRow.setAlignment(Pos.CENTER);

        TextField phoneNumField = new TextField(s.getPhoneNum());
        HBox phonNumRow = new HBox(5, new Label("Phone Number"), phoneNumField);
        phonNumRow.setAlignment(Pos.CENTER);

        TextField passworField = new TextField(s.getPassword());
        HBox passwordRow = new HBox(5, new Label("Password: "), passworField);
        passwordRow.setAlignment(Pos.CENTER);

        TextField confirmationField = new TextField(s.getPassword());
        HBox confirmationRow = new HBox(5, new Label("Confirmation: "), confirmationField);
        confirmationRow.setAlignment(Pos.CENTER);

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton managerBtn = new RadioButton("Manager");
        RadioButton staffBtn = new RadioButton("Staff");
        managerBtn.setToggleGroup(toggleGroup);
        staffBtn.setToggleGroup(toggleGroup);
        HBox statusRow = new HBox(5, new Label("Access Permission: "), managerBtn, staffBtn);
        statusRow.setAlignment(Pos.CENTER);

        if (s.getPermission().equals("Manager")) {
            managerBtn.setSelected(true);
        } else {
            staffBtn.setSelected(true);
        }

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            if (managerBtn.isSelected()) {
                controller.editStaff(s, nameField.getText(), phoneNumField.getText(), passworField.getText(),
                        confirmationField.getText(), "Manager");
                stage.close();
            } else {
                controller.editStaff(s, nameField.getText(), phoneNumField.getText(), passworField.getText(),
                        confirmationField.getText(), "Staff");
                stage.close();
            }
        });

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        HBox btnRow = new HBox(5, saveBtn, closeBtn);
        btnRow.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, nameRow, phonNumRow, passwordRow, confirmationRow, statusRow, btnRow);
        root.setAlignment(Pos.CENTER);

        Scene addStaffScene = new Scene(root, 400, 200);

        stage.setScene(addStaffScene);
        stage.show();
    }

    public void createLoginErrorPage() { // This is the pop-up window to the user if the login is failed
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label errorMessage = new Label("Login Failed, Please Check Your ID and Password");

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        VBox root = new VBox(5, errorMessage, closeBtn);
        root.setAlignment(Pos.CENTER);

        Scene loginErrorScene = new Scene(root, 300, 150);

        stage.setScene(loginErrorScene);
        stage.show();
    }

    public void createPriceErrorPage() { // This is the pop-up window to the user set the wrong price
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label errorMessage = new Label("Modifying Unsucessful, Invalid Input");

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        VBox root = new VBox(5, errorMessage, closeBtn);
        root.setAlignment(Pos.CENTER);

        Scene priceErrorScene = new Scene(root, 300, 150);

        stage.setScene(priceErrorScene);
        stage.show();
    }

    public void createPasswordErrorPage() { // This is the pop-up window if the password doesn't matach with the
                                            // confirmation
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label errorMessage = new Label("Password Doesn't Match");

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        VBox root = new VBox(5, errorMessage, closeBtn);
        root.setAlignment(Pos.CENTER);

        Scene passwordErrorScene = new Scene(root, 300, 150);

        stage.setScene(passwordErrorScene);
        stage.show();
    }

    public void createEmptyErrorPage() { // This is the pop-up window if the staff's information is empty
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label errorMessage = new Label("Staff's Information Cannot be Empty");

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        VBox root = new VBox(5, errorMessage, closeBtn);
        root.setAlignment(Pos.CENTER);

        Scene emptyErrorScene = new Scene(root, 300, 150);

        stage.setScene(emptyErrorScene);
        stage.show();
    }

    public void createBurgerErrorPage() { // This is the pop-up window if there's invalid input while customizing the
                                          // burger
        Stage stage = new Stage();
        stage.setTitle("ERROR");
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label errorMessage = new Label("Conirmation Failed, Invalid Input");

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(event -> stage.close());

        VBox root = new VBox(5, errorMessage, closeBtn);
        root.setAlignment(Pos.CENTER);

        Scene burgerErrorScene = new Scene(root, 300, 150);

        stage.setScene(burgerErrorScene);
        stage.show();
    }
}