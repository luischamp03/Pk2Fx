package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.game.GameArea;
import javafx.scene.image.Image;

import static edu.masanz.da.pk2.game.AppConsts.*;

public abstract class AFlyingEnemy extends AEnemy {

    public AFlyingEnemy(Image img, int rows, int cols) {
        super(img, rows, cols);
    }

    public AFlyingEnemy(Image img, int rows, int cols, int rowSpace, int colSpace) {
        super(img, rows, cols, rowSpace, colSpace);
    }


    @Override
    public void update() {
        if (x > GameArea.rect.right - width - xSpeed || x + xSpeed < GameArea.rect.left) {
            xSpeed = -xSpeed;
        }
        if (y + height + ySpeed > GameArea.rect.bottom || y + ySpeed < GameArea.rect.top) {
            ySpeed = -ySpeed;
        }
        x = x + xSpeed;
        y = y + ySpeed;
        updateFrame();
    }

    public void updateFrame(){
        if (Math.abs(xSpeed) < Math.abs(ySpeed) && ySpeed < 0) {
            side = E_DLRU_UP;
        }else if (Math.abs(xSpeed) < Math.abs(ySpeed) && ySpeed > 0) {
            side = E_DLRU_DOWN;
        }else if (Math.abs(xSpeed) > Math.abs(ySpeed) && xSpeed > 0) {
            side = E_DLRU_RIGHT;
        }else if (Math.abs(xSpeed) > Math.abs(ySpeed) && xSpeed < 0) {
            side = E_DLRU_LEFT;
        }
        if (xSpeed != 0 || ySpeed != 0){
            currentFrame = ++currentFrame%cols;
        }else {
            currentFrame = 0;
        }
    }

}
