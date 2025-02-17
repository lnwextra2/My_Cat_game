package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import logic.game.GameController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameController.setupScene();
        primaryStage.setScene(GameController.getScene());
        primaryStage.setTitle("The Cat and Dog");
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
