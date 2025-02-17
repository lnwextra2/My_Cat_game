package logic.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.game.GameController;
import java.util.Random;

public class Cat {
    private double x, y;
    private final double WIDTH=50,HEIGHT=51;
    private double speed;
    private boolean isUseSpecialAbility = true;
    private final Image catImage = new Image(ClassLoader.getSystemResource("res/cat.png").toString());

    public Cat(){
        Random rand = new Random();
        this.x = rand.nextInt(800);
        this.y = rand.nextInt(600);
        speed = 20;
        useSpecialAbility();
        
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
    public void useSpecialAbility() {
        /*====================FILL CODE============================
        Add something on the code below to make special ability work.	
        */
    	new Thread(()->{
        while (!GameController.isGameEnded()) {
            try {
            	
                if (GameController.getKeyboardController().isPPressed()) {
                    isUseSpecialAbility = true;
                    /*====================FILL CODE============================
				    Add something to activate ability for 1 second and then cool down
			            (do not allow to activate) for 5 second
                     */
                    Thread.sleep(1000);
                    isUseSpecialAbility = false;
                    Thread.sleep(5000);
                }
                 /*DO NOT CHANGE THIS LINE*/
                Thread.sleep(30);
                /*========================================================*/
                
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }).start();
    }

    public void render(GraphicsContext gc){
        gc.drawImage(catImage,getX(),getY(),WIDTH,HEIGHT);
    }

    public void move(int dirLR, int dirUD){
        if(GameController.isGameEnded())return;
        this.x += dirLR*speed;
        this.y += dirUD*speed;
        if(this.x<0)this.x=0;
        if(this.x>750)this.x=750;
        if(this.y<0)this.y=0;
        if(this.y>550)this.y=550;
    }

    public boolean isUseSpecialAbility() {
        return isUseSpecialAbility;
    }
    
    public double getWidth() {
        return WIDTH;
    }

    public double getHeight() {
        return HEIGHT;
    }

}
