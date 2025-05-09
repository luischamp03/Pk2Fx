package edu.masanz.da.pk2.sprite.weaponry;

import javafx.scene.image.Image;

import static edu.masanz.da.pk2.game.AppConsts.SHOT_SPEED;

/**
 * Normal shot without animation but in multiple possible directions
 */
public class Shot extends AShot {

    // region attributes
    private static int ROWS = 1;
    private static int COLS = 1;
    //endregion

    public Shot(Image img, int xSpeed, int ySpeed) {
        super(img,ROWS,COLS);
        double h = Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed);
        this.xSpeed = (int) (SHOT_SPEED * xSpeed / h);
        this.ySpeed = (int) (SHOT_SPEED * ySpeed / h);
    }

    public void setPos(int x, int y){
        // there is a position correction because it is going to be painted after an update
        // and the first time it would be painted in the wrong position, no where it was created
        x = x - xSpeed;
        y = y + ySpeed;
        super.setPos(x, y);
    }

    public void update() {
        x = x + xSpeed;
        y = y - ySpeed;
    }

}
