package FX;

import Models.Animals.Animal;
import Models.Animals.Cat;
import Models.Animals.Dog;
import Models.Animals.TypeAdapters.CatAdapter;
import Models.Animals.TypeAdapters.DogAdapter;
import Models.Enums.AnimalType;
import Models.Enums.Gender;
import Models.Reservations.Reservation;
import Models.Shops.Product;
import Models.Shops.Shop;
import Models.Shops.ShopManager;
import com.google.gson.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.GenericDeclaration;
import java.text.DecimalFormat;

public class Main extends Application {

    private ShopManager shopManager;
    private Reservation reservation;

    private TextField tbBadHabits;
    private ListView<Animal> lvAnimals;
    private ListView<Product> lvProducts;
    private TextField tbReservor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage window = primaryStage;
        window.setTitle("Animal Shelter");
        window.setOnCloseRequest(e ->{
            e.consume();
            exitApplication();
        });
        reservation = new Reservation();
        shopManager = new ShopManager();
        //readFile();

        //<editor-fold desc="Adding new animals to the shelter">
        VBox vbNewAnimals = new VBox(20);
            VBox vbSpecies = new VBox(5);
                Label lblSpecies = new Label("Species:");
                ObservableList<String> speciesList =
                        FXCollections.observableArrayList(
                                "Cat",
                                "Dog"
                        );
                ComboBox cbSpecies = new ComboBox(speciesList);
                cbSpecies.setValue("Dog");
                cbSpecies.valueProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object o, Object t1) {
                        if (cbSpecies.getValue().toString() == "Dog"){
                            tbBadHabits.setDisable(true);
                            tbBadHabits.clear();
                        }
                        else{
                            tbBadHabits.setDisable(false);
                        }
                    }});
            vbSpecies.getChildren().addAll(lblSpecies, cbSpecies);

            VBox vbName = new VBox(5);
                Label lblName = new Label("Name:");
                TextField tbName = new TextField();
            vbName.getChildren().addAll(lblName, tbName);

            VBox vbGender = new VBox(5);
                Label lblGender = new Label("Gender:");
            ToggleGroup tgGender = new ToggleGroup();

            HBox hbGender = new HBox(10);
                RadioButton rbMale = new RadioButton("Male");
                rbMale.setToggleGroup(tgGender);
                rbMale.setSelected(true);
                RadioButton rbFemale = new RadioButton("Female");
                rbFemale.setToggleGroup(tgGender);
                hbGender.getChildren().addAll(rbMale, rbFemale);
            vbGender.getChildren().addAll(lblGender, hbGender);

            VBox vbBadHabits = new VBox(5);
                Label lblBadHabits = new Label("Bad habits:");
                tbBadHabits = new TextField();
                tbBadHabits.setDisable(true);
            vbBadHabits.getChildren().addAll(lblBadHabits, tbBadHabits);

            Button btnAddAnimal = new Button("Add Animal");
            btnAddAnimal.setOnAction(e -> {
                boolean check = addAnimal(cbSpecies.getValue().toString(), tbName.getText(), rbMale.isSelected(), tbBadHabits.getText());
                if(!check){
                    new AlertBox().display("Error!", "Fill in all fields correctly.");
                }
                tbName.clear();
            });
        vbNewAnimals.getChildren().addAll(vbSpecies, vbName, vbGender, vbBadHabits, btnAddAnimal);
        //</editor-fold>

        //<editor-fold desc="All animals in the shelter and option to reserve">
        VBox vbAllAnimals = new VBox(20);
            VBox vbAnimals = new VBox(5);
                Label lblAnimals = new Label("Animals:");
                lvAnimals = new ListView<>();
                setLvAnimals();
                lvAnimals.setPrefHeight(100);
            vbAnimals.getChildren().addAll(lblAnimals, lvAnimals);
            VBox vbReservation = new VBox(5);
                Label lblReserve = new Label("Reserve animal:");
                HBox hbReserve = new HBox(5);
                    Label lblReservor = new Label("Name:");
                    tbReservor = new TextField();
                    Button btnReserve = new Button("Reserve selected animal");
                    btnReserve.setOnAction(e -> {
                        if (!tbReservor.getText().equals("")){
                            if (!reserveAnimal()){
                                new AlertBox().display("Error!", "This animal is already reserved.");
                            }
                        }
                        else{
                            new AlertBox().display("Error!", "Enter a name when reserving an animal.");
                        }
                        tbReservor.clear();
                    });
                hbReserve.getChildren().addAll(lblReservor, tbReservor, btnReserve);
            vbReservation.getChildren().addAll(lblReserve, hbReserve);
        vbAllAnimals.getChildren().addAll(vbAnimals, vbReservation);
        //</editor-fold>

        //<editor-fold desc="Add products or animals to the shop">
        VBox vbAddToShop = new VBox(20);
            VBox vbProductName = new VBox(5);
                Label lblProductName = new Label("Product name:");
                TextField tbProductName = new TextField();
            vbProductName.getChildren().addAll(lblProductName, tbProductName);
            VBox vbProductPrice = new VBox(5);
                Label lblProductPrice = new Label("Price:");
                Spinner<Double> spProductPrice = new Spinner<>(0.00, 100.00, 0.0, 0.01);
                spProductPrice.setEditable(true);
            vbProductPrice.getChildren().addAll(lblProductPrice, spProductPrice);
            Button btnAddToShop = new Button("Add product");
            btnAddToShop.setOnAction(e -> {
                addProductToShop(tbProductName.getText(), spProductPrice.getValue());
                setLvProducts();
            });
        vbAddToShop.getChildren().addAll(vbProductName, vbProductPrice, btnAddToShop);
        //</editor-fold>

        //<editor-fold desc="All products in the shop">
        VBox vbAllProducts = new VBox(20);
            VBox vbProducts = new VBox(5);
                Label lblProducts = new Label("Products:");
                lvProducts = new ListView<>();
                setLvProducts();
                lvProducts.setPrefHeight(100);
            vbProducts.getChildren().addAll(lblProducts, lvProducts);
        vbAllProducts.getChildren().addAll(vbProducts);
        //</editor-fold>

        HBox hbAnimalShelter = new HBox(20);
        hbAnimalShelter.getChildren().addAll(vbNewAnimals, vbAllAnimals, vbAddToShop, vbAllProducts);

        Scene scene = new Scene(hbAnimalShelter, 1000, 500);
        window.setScene(scene);
        window.show();
    }

    private void setLvProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Product product : shopManager.getShop().getProducts()) {
            products.add(product);
        }
        lvProducts.setItems(products);
    }


    private boolean addProductToShop(String productName, Double price) {
        if (!productName.equals("")){
            if (shopManager.addProduct(new Product(productName, price))){
                return true;
            }
            new AlertBox().display("Error!", "The price has to be at least 0.01 euro.");
            return false;
        }
        new AlertBox().display("Error!", "Please enter a product name.");
        return false;
    }

    private void readFile(){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + ".txt"));
            String line = reader.readLine();
            while(line != null){
                reservation.getAnimals().add(constructAnimal(line));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            new AlertBox().display("Error!", "Something went wrong... Your animals might not be loaded properly.");
        } catch (IOException e) {
            new AlertBox().display("Error!", "Something went wrong... Your animals might not be loaded properly.");
        }
    }

    private Animal constructAnimal(String line) {
        Animal animal = null;

        if (line.contains("Dog")){
            animal = new Dog();
        }
        else if (line.contains("Cat")){
            animal = new Cat();
        }

        animal.toString();

        return animal;
    }

    private void exitApplication() {
        boolean answer = new ConfirmBox().display("Exit application", "Are you sure you want to close the application?");

        if (answer){
            try{
                File newAnimals = new File(System.getProperty("user.dir") + ".txt");
                FileWriter fw = new FileWriter(newAnimals, false);

                for(Animal animal : reservation.getAnimals()){
                    if (animal.getAnimalType() == AnimalType.Dog){
                        fw.write("Dog: ");
                    }
                    else if (animal.getAnimalType() == AnimalType.Cat){
                        fw.write("Cat: ");
                    }

                    fw.write(animal.toString());
                    fw.write(System.getProperty("line.separator"));
                }
                fw.close();
            } catch (IOException e) {
                new AlertBox().display("Error!", "Something went wrong... Your animals might not be saved properly.");
            }

            Platform.exit();
        }
    }

    private void setLvAnimals(){
        ObservableList<Animal> animals = FXCollections.observableArrayList();
        for(Animal animal : reservation.getAnimals()){
            animals.add(animal);
        }
        lvAnimals.setItems(animals);
    }

    private boolean reserveAnimal() {
        Animal animal = lvAnimals.getSelectionModel().getSelectedItem();

        if(animal.getReservedBy() == null){
            animal.Reserve(tbReservor.getText());
            setLvAnimals();
            return true;
        }

        return false;
    }

    private boolean addAnimal(String species, String name, boolean male, String badHabits) {
        if(name.equals("") || (species.equals("Dog") ? false : badHabits.equals(""))){
            return false;
        }

        Gender gender = male ? Gender.Male : Gender.Female;
        switch(species){
            case "Dog":
                reservation.NewDog(name, gender);
                break;
            case "Cat":
                reservation.NewCat(name, gender, badHabits);
                break;
        }

        setLvAnimals();
        return true;
    }
}
