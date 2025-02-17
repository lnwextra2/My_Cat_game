package logic.game;

import java.lang.management.PlatformLoggingMXBean;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import logic.components.*;
public class GameController {
    private static VBox root;
    private static MenuPane menuPane;
    private static FieldCanvas fieldCanvas;
    private static Scene scene;
    private static boolean isGameEnded;
    private static KeyboardController keyboardController;

    public static void setupScene() {
        root = new VBox();
        menuPane = MenuPane.getInstance();
        scene = new Scene(root, 800, 625);
        keyboardController = new KeyboardController();
        fieldCanvas = new FieldCanvas(800, 600);
        fieldCanvas.setFocusTraversable(true);
        root.getChildren().addAll(menuPane,fieldCanvas);
        isGameEnded = false;
        updateScore();
    }



    public static void updateScore(){
        /*====================FILL CODE============================
        Add something on the code below to make score properly updatable.
        */
    	new Thread(()->{
        while(!GameController.isGameEnded()){
            try{
                /*DO NOT CHANGE THIS LINE*/
                Thread.sleep(1000);
                /*========================================================*/
                Platform.runLater(()->{
                menuPane.increaseScore();
                });
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    	}).start();
    }

    public static boolean isGameEnded() {
        return isGameEnded;
    }
    public static void setIsGameEnded(boolean isGameEnded){
        GameController.isGameEnded = isGameEnded;
        if(isGameEnded)menuPane.setGameOverText();
    }
    public static Scene getScene() {
        return scene;
    }

    public static KeyboardController getKeyboardController() {
        return keyboardController;
    }
}
