package logic.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.game.GameController;

public class Camera_Range {
    private double rangeX = 300;  // ความกว้างของกรอบซ้าย-ขวา
    private double rangeY = 300;  // ความสูงของกรอบบน-ล่าง
    private double x , y;  // ตำแหน่งซ้ายบนของกล้อง
    private Cat target;
    
    public Camera_Range(Cat target) {
        this.target = target;
        this.x = target.getX() - rangeX / 2;
        this.y = target.getY() - rangeY / 2;
    }
    
    public void update() {
        // ตำแหน่งของแมว
        double catX = target.getX();
        double catY = target.getY();
        double catWidth = target.getWidth();
        double catHeight = target.getHeight();

        // อัปเดตตำแหน่งกรอบกล้องให้ตามแมว โดยให้แมวอยู่กลางกรอบเสมอ
        x = catX + catWidth/2 - rangeX /2;
        y = catY + catHeight/2 - rangeY /2;
        
        // ป้องกันไม่ให้กรอบกล้องออกนอกขอบ Map
        double sceneWidth = GameController.getScene().getWidth();
        double sceneHeight = GameController.getScene().getHeight();
        
        // จำกัดตำแหน่ง x และ y ให้อยู่ในขอบเขตของ Scene
        x = Math.max(0, Math.min(x, sceneWidth - rangeX));
        y = Math.max(0, Math.min(y, sceneHeight - rangeY));
        
        System.out.println("Camera X: " + x + " Y: " + y);
    }
    
    public void render(GraphicsContext gc){
       
        gc.strokeRect(x, y, rangeX, rangeY);
         gc.setStroke(Color.RED);
        gc.setLineWidth(3);
    }

    public double getRangeX() {
        return rangeX;
    }

    public double getRangeY() {
        return rangeY;
    }

    public Cat getTarget() {
        return target;
    }

    public void setTarget(Cat target) {
        this.target = target;
    }
}
//