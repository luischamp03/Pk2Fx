package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.game.SceneLoader;
import javafx.scene.image.Image;
import org.apache.log4j.Logger;

import static edu.masanz.da.pk2.game.AppConsts.*;

public abstract class AWaterEnemy extends AEnemy {
    private static Logger log = Logger.getLogger(AGroundEnemy.class);

    public AWaterEnemy(Image img, int rows, int cols) {
        super(img, rows, cols);
    }

    public AWaterEnemy(Image img, int rows, int cols, int rowSpace, int colSpace) {
        super(img, rows, cols, rowSpace, colSpace);
    }

    @Override
    public void update() {
        int nextX = x + xSpeed;
        int nextY = y + ySpeed;
        // check if the next position is inside water, this is '3' in the map
        // +width/2 is used to check the horizontal center of the sprite, more realistic
        if (SceneLoader.getPosValue(nextX + width/2, nextY) == SL_V_WATER){
            x = nextX;
            y = nextY;
        }else {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
            x = x + xSpeed;
            y = y + ySpeed;
        }
        updateFrame();
    }

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
