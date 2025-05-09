package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.game.GameArea;
import edu.masanz.da.pk2.game.SceneLoader;
import javafx.scene.image.Image;
import org.apache.log4j.Logger;

import static edu.masanz.da.pk2.game.AppConsts.*;

public abstract class AGroundEnemy extends AEnemy {
    private static Logger log = Logger.getLogger(AGroundEnemy.class);

    public AGroundEnemy(Image img, int rows, int cols) {
        super(img, rows, cols);
    }

    public AGroundEnemy(Image img, int rows, int cols, int rowSpace, int colSpace) {
        super(img, rows, cols, rowSpace, colSpace);
    }

    @Override
    public void update() {
        int nextX = x + xSpeed;
        int nextY = y + ySpeed;
        // check if the next pocsition is inside a path, this is '0' value in the map
        // as two axis are checked, the sprite can move diagonally if possible,
        // or in one axis if the other is blocked, this is good for bending the corners
        // +width/2 is used to check the horizontal center of the sprite, more realistic
        // -height/4 is used to check the feet of the sprite due to zenital view
        if (SceneLoader.getPosValue(nextX+width/2, nextY-height/4) <= SL_V_FLOOR){
            x = nextX;
            y = nextY;
        }else if (SceneLoader.getPosValue(x+width/2, nextY-height/4) <= SL_V_FLOOR){
            y = nextY;
            xSpeed = -xSpeed;
        }else if (SceneLoader.getPosValue(nextX+width/2, y-height/4) <= SL_V_FLOOR){
            x = nextX;
            ySpeed = -ySpeed;
        }else {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
            x = x + xSpeed;
            y = y + ySpeed;
        }
        updateFrame();
    }

//    @Override
//    public void update() {
//        int nextX = x + xSpeed;
//        int nextY = y + ySpeed;
//        // check if the next position is inside a path, this is '0', '1' or '2' in the map
//        // as two axis are checked, the sprite can move diagonally if possible,
//        // or in one axis if the other is blocked, this is good for bending the corners
//        // +width/2 is used to check the horizontal center of the sprite, more realistic
//        // -height/4 is used to check the feet of the sprite due to zenital view
//        if (SceneLoader.getPosValue(nextX+width/2, nextY-height/1) <= SL_V_FLOOR){
//            x = nextX;
//            y = nextY;
//        }else if (SceneLoader.getPosValue(x+width/2, nextY-height/1) <= SL_V_FLOOR){
//            y = nextY;
//            xSpeed = -xSpeed;
//        }else if (SceneLoader.getPosValue(nextX+width/2, y-height/1) <= SL_V_FLOOR){
//            x = nextX;
//            ySpeed = -ySpeed;
//        }else {
//            xSpeed = -xSpeed;
//            ySpeed = -ySpeed;
//            x = x + xSpeed;
//            y = y + ySpeed;
//        }
//        updateFrame();
//    }

    public void updateFrame(){
        if (Math.abs(xSpeed) <= Math.abs(ySpeed) && ySpeed <= 0) {
            side = E_DLRU_UP;
        }else if (Math.abs(xSpeed) <= Math.abs(ySpeed) && ySpeed > 0) {
            side = E_DLRU_DOWN;
        }else if (Math.abs(xSpeed) >= Math.abs(ySpeed) && xSpeed >= 0) {
            side = E_DLRU_RIGHT;
        }else if (Math.abs(xSpeed) >= Math.abs(ySpeed) && xSpeed < 0) {
            side = E_DLRU_LEFT;
        }
        if (xSpeed != 0 || ySpeed != 0){
            currentFrame = ++currentFrame%cols;
        }else {
            currentFrame = 0;
        }
    }

}
