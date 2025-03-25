package iut.gon.agarioclient.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private TextField nicknameField;

    @FXML
    private Button onlineButton;

    @FXML
    private Button localButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void onOnlineButtonClick(ActionEvent event) {
        String nickname = nicknameField.getText().trim();
        if (nickname.isEmpty()) {
            showAlert("Erreur", "Veuillez entrer un surnom !");
        } else {
            try {
                FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/iut/gon/agarioclient/game-view.fxml"));
                Parent gameView = gameLoader.load();

                FXMLLoader rightPanelLoader = new FXMLLoader(getClass().getResource("/iut/gon/agarioclient/chat-view.fxml"));
                Parent rightPanelView = rightPanelLoader.load();

                HBox hbox = new HBox();
                hbox.getChildren().addAll(gameView, rightPanelView);
                hbox.setSpacing(10);

                GameController gameController = gameLoader.getController();
                gameController.initializeGame(nickname);

                Scene gameScene = new Scene(hbox);
                stage.setScene(gameScene);
                stage.setTitle("Jeu en ligne");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    private void onLocalButtonClick(ActionEvent event) {
        String nickname = nicknameField.getText().trim();
        if (nickname.isEmpty()) {
            showAlert("Erreur", "Veuillez entrer un surnom !");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/iut/gon/agarioclient/game-view.fxml"));
                Parent newView = loader.load();

                GameController gameController = loader.getController();
                gameController.initializeGame(nickname);

                Scene gameScene = new Scene(newView);
                stage.setScene(gameScene);
                stage.setTitle("Jeu en local");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}