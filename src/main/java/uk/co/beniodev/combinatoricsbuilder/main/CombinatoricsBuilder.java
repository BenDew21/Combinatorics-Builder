package uk.co.beniodev.combinatoricsbuilder.main;

import javafx.application.Application;
import javafx.stage.Stage;
import uk.co.beniodev.combinatoricsbuilder.utilities.StageHandler;

public class CombinatoricsBuilder extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageHandler handler = new StageHandler();
        handler.openStage(primaryStage, "MainScreen", "Combinatorics Builder");
    }
}
