package de.rocketman.view;


import de.rocketman.MainApp;
import de.rocketman.model.Seminar;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SeminarOverviewController {

    @FXML
    private TableView<Seminar> seminarTable;
    @FXML
    private TableColumn<Seminar, Integer> nummerColumn;
    @FXML
    private TableColumn<Seminar, String> themaColumn;

    @FXML
    private Label nummerLabel;
    @FXML
    private Label themaLabel;
    @FXML
    private Label ortLabel;
    @FXML
    private Label anzahlTageLabel;

    // Reference to the main application
    private MainApp mainApp;

    /**
     * The constructor
     * The constructor is called before the initiaze() method;
     */
    public SeminarOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        nummerColumn.setCellValueFactory(cellData -> cellData.getValue().nummerProperty().asObject());
        themaColumn.setCellValueFactory(cellData -> cellData.getValue().themaProperty());

        showSeminarDetails(null);

        seminarTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSeminarDetails(newValue));
    }

    /**
     * Is called by the main application to give a referene back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        seminarTable.setItems(mainApp.getSeminarData());
    }

    private void showSeminarDetails(Seminar seminar){
        if (seminar != null) {
            nummerLabel.setText((Integer.toString(seminar.getNummer())));
            themaLabel.setText(seminar.getThema());
            ortLabel.setText(seminar.getOrt());
            anzahlTageLabel.setText((Integer.toString(seminar.getAnzahlTage())));
        } else {
            nummerLabel.setText("");
            themaLabel.setText("");
            ortLabel.setText("");
            anzahlTageLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteSeminar() {
        int selectedIndex = seminarTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >=0) {
            seminarTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Keine Auswahl");
            alert.setHeaderText("Kein Seminar selektiert");
            alert.setContentText("Bitte wählen Sie ein Seminar in der Tabelle aus");
            alert.showAndWait();
        }
    }
}