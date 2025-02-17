package logic.components;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logic.game.GameController;


public class MenuPane extends GridPane {
    private Text scoreText;
    private int score = 0;
    private int hp = 0;
    private Text gameOverText;
    private Button endGameButton;
    private static MenuPane instance=null;
    public MenuPane(){
        super();
        score = 0;
        hp = 100;
        gameOverText = new Text();
        scoreText= new Text("Score : 0");
        endGameButton = new Button("End Game");
        endGameButton.setOnAction((e)->{
            GameController.setIsGameEnded(true);
        });
        add(endGameButton,0,0);
        add(gameOverText,1,0);
        add(scoreText,2,0);
        ColumnConstraints c0 = new ColumnConstraints();
        c0.setPercentWidth(33);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(33);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(33);
        getColumnConstraints().addAll(c0, c1, c2);
        setHalignment(endGameButton, HPos.LEFT);
        setHalignment(gameOverText, HPos.CENTER);
        setHalignment(scoreText, HPos.RIGHT);
    }
    public static MenuPane getInstance(){
        if(instance == null){
            instance = new MenuPane();
        }
        return instance;
    }
    public void increaseScore(){
        score += 1;
        scoreText.setText("Score : "+ score);
    }
    public void setGameOverText(){
        gameOverText.setText("GAME OVER");
    }
}
