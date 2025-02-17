package logic.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.game.GameController;
import java.util.Random;

public class Dog {
    private double x, y;
    private double speed;
    private Cat target;
    private final double WIDTH=50,HEIGHT=51;
    private final Image DogImage = new Image(ClassLoader.getSystemResource("res/dog.png").toString());

    public Dog(Cat target){
        Random rand = new Random();
        this.x = rand.nextInt(800);
        this.y = rand.nextInt(600);
        this.target = target;
        speed = 5;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public Cat getTarget(){
        return this.target;
    }
    
    public void render(GraphicsContext gc){
        gc.drawImage(DogImage,getX(),getY(),WIDTH,HEIGHT);
    }
    
    public void move(){
        if(GameController.isGameEnded()|| target.isUseSpecialAbility())return;
        double dy = getTarget().getY() - getY();
        double dx = getTarget().getX() - getX();
        double angle = Math.atan2( dy , dx );
        double goX = speed * Math.cos(angle);
        double goY = speed * Math.sin(angle);

        this.y += goY;
        this.x += goX;

        dy=getTarget().getY()-getY();
        dx=getTarget().getX()-getX();
        if(dy < 50 && dx < 50 && dy > -50 && dx > -50){
            GameController.setIsGameEnded(true);
        }
    }

}
