package logic.components;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.game.GameController;
import logic.game.KeyboardController;
public class FieldCanvas extends Canvas{
    private final Image sandImage = new Image(ClassLoader.getSystemResource("res/sand.jpg").toString());
    private final Cat cat;
    private final ArrayList<Dog> dogs;
    private final Camera_Range camera;			//new
    
    public FieldCanvas(double width, double height){
        super(width,height);
        this.cat = new Cat();
        this.dogs = new ArrayList<Dog>();
        
        this.camera = new Camera_Range(cat);	//new
        
        setUpMove();
        spawnDog();
        updateCanvas(getGraphicsContext2D());
    }

    public void spawnDog(){
    	new Thread(()->{
        int count = 0;
        while (!GameController.isGameEnded()) {
        	
            try{
                dogs.add(new Dog(cat));
                System.out.println("add new Dog");
                Thread.sleep(5000);
                count++;
                if(count >= 3){
                    dogs.remove(0);
                }
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }

        }
    	}).start();
    }
    public void setUpMove(){
       
    	new Thread(()->{
        KeyboardController keyboard = GameController.getKeyboardController();
        int dirUD,dirLR;
        while(!GameController.isGameEnded()){
            try{
                dirLR=0;
                dirUD=0;
                if(keyboard.isUpPressed()) dirUD = -1;
                else if(keyboard.isDownPressed()) dirUD = 1;
                if(keyboard.isLeftPressed()) dirLR = -1;
                else if(keyboard.isRightPressed()) dirLR = 1;
                cat.move(dirLR,dirUD);
                camera.update();		//new
                
                for(int i=0;i<dogs.size();i++){
                    dogs.get(i).move();
                }

                Thread.sleep(30);
             
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    	}).start();
    }

    public void updateCanvas(GraphicsContext gc) {
    
    	new Thread(()->{
        while(!GameController.isGameEnded()) {
            try{
            
            	Platform.runLater(()->{
                gc.clearRect(0, 0, 800, 600);
                gc.drawImage(sandImage, 0, 0, 800, 600);
                cat.render(gc);
                for (int i = 0; i < dogs.size(); i++) {
                    dogs.get(i).render(gc);
                }
                
                camera.render(gc);
            	});
            	
            	
            	
                Thread.sleep(30);
              
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    	}).start();
    }
    
}
