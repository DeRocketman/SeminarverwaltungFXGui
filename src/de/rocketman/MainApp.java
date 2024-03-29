package de.rocketman;

import de.rocketman.model.Seminar;
import de.rocketman.view.SeminarOverviewController;
import de.rocketman.view.SeminarEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList <Seminar> seminarData = FXCollections.observableArrayList();

    public MainApp(){
        seminarData.add(new Seminar(123, "Lübeck", "Datenbanken", 3));
        seminarData.add(new Seminar(124, "Hamburg", "Projektmanagement", 8));
        seminarData.add(new Seminar(125, "Flensburg", "UL1", 6));
        seminarData.add(new Seminar(126, "Waldenau", "Betriebssysteme", 5));
        seminarData.add(new Seminar(127, "Waldenau", "Programmieren 1", 13));
        seminarData.add(new Seminar(128, "Lübeck", "Programmieren 2", 43));
        seminarData.add(new Seminar(129, "Lübeck", "Datenbanken 2", 2));
        seminarData.add(new Seminar(130, "Lübeck", "Haekeln mit Omi", 3));
        seminarData.add(new Seminar(131, "Lübeck", "Kniffeln", 10));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Schulungsverwaltung");

        initRootLayout();
        showSeminarOverview();
    }

    public ObservableList<Seminar> getSeminarData() {
        return seminarData;
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showSeminarOverview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Seminaruebersicht.fxml"));
            AnchorPane seminarOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(seminarOverview);

            SeminarOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean showSeminarEditDialog(Seminar seminar){
        try {
            // Load the fxml file and create the new stage for the popup dialog-
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SeminarEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the seminar into the controller.
            SeminarEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSeminar(seminar);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
