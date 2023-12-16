package co.mizrahi.launcher;

import co.mizrahi.launcher.constants.Players;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static java.util.Objects.isNull;

public class LoaderController implements Initializable {

    @FXML
    public TextField textField;
    @FXML
    public ComboBox<Players> players;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.checkAndInstall();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't install required components");
        }
        Arrays.stream(Players.values())
                .forEach(player -> this.players.getItems().add(player));
    }

    private void checkAndInstall() throws IOException, InterruptedException {
        int isNodeInstalled = checkIfInstalled("node -v");
        if (isNodeInstalled != 0) {
            this.showAlert("Installing NodeJS", Alert.AlertType.INFORMATION);
            this.install("brew install node");
        }
        int isPeerflixInstalled = checkIfInstalled("peerflix --version");
        if (isPeerflixInstalled != 0) {
            this.showAlert("Installing Peerflix", Alert.AlertType.INFORMATION);
            this.install("npm install -g peerflix");
        }
    }

    private static int checkIfInstalled(String command) throws IOException, InterruptedException {
        Process checkNode = Runtime.getRuntime().exec(command);
        return checkNode.waitFor();
    }

    private void install(String command) throws IOException, InterruptedException {
        Process installNode = Runtime.getRuntime().exec(command);
        installNode.waitFor();
    }

    @FXML
    protected void onPlayButtonClick() {
        Players selectedItem = this.players.getSelectionModel().getSelectedItem();
        if (isNull(selectedItem)) {
            this.showAlert("Choose player!", Alert.AlertType.ERROR);
            return;
        }
        String player = selectedItem.getPlayer();
        String link = this.textField.getCharacters().toString();
        if (link.isBlank() || link.isEmpty()) {
            this.showAlert("Paste magnet link or choose torrent file!", Alert.AlertType.ERROR);
            return;
        }
        this.play(link, player);
    }

    private void play(String link, String player) {
        try {
            String command = String.format("peerflix %s -a -r %s", link, player);
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            Platform.exit();
        } catch (IOException | InterruptedException e) {
            this.showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void onSelectFileClick(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Window stage = source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Torrent files (*.torrent)", "*.torrent");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        String absolutePath = file.getAbsolutePath();
        this.textField.setText(absolutePath);
        this.textField.setAccessibleText(absolutePath);
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert errorAlert = new Alert(alertType);
        errorAlert.setHeaderText(alertType.name());
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }
}
