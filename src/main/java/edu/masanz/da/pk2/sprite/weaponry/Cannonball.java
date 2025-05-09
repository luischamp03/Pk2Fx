package edu.masanz.da.pk2.sprite.weaponry;

import javafx.scene.image.Image;

import static edu.masanz.da.pk2.game.AppConsts.BALL_SPRITE_IMAGE;

/**
 * Disparo sin animación
 */
public class Cannonball extends AShot {

    // region attributes
    static int ROWS = 1;
    static int COLS = 1;
    static final int MAX_SPEED = 10;
    //endregion

    public Cannonball() {
        super(BALL_SPRITE_IMAGE,ROWS,COLS);
        xSpeed = 0;
        ySpeed = MAX_SPEED;
    }

    public void update() {
        x = x + xSpeed;
        y = y + ySpeed;
    }

}
