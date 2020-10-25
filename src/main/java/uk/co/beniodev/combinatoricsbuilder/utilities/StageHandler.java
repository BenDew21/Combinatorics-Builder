package uk.co.beniodev.combinatoricsbuilder.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StageHandler {

    public void openStage(Stage stage, String stageName, String windowName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/" + stageName + ".fxml"));
        Parent content = loader.load();

        Scene scene = new Scene(content);

        stage.setScene(scene);
        stage.setTitle(windowName);
        stage.show();
    }
}
