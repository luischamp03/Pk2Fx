package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.sprite.weaponry.BurstGun;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class Miau extends AGroundEnemy {

    private int n = 0;

    public Miau() {
        super(E_GROUND_MIAU_SPRITE_IMAGE, E_GROUND_MIAU_SPRITE_ROWS, E_GROUND_MIAU_SPRITE_COLS,
                E_GROUND_MIAU_SPRITE_ROWSPACE, E_GROUND_MIAU_SPRITE_COLSPACE);
        setSpeed(E_GROUND_MIAU_SPEED, 0);
    }

    public Miau(int angleDegrees) {
        this();
        setSpeed(E_GROUND_MIAU_SPEED, angleDegrees);
    }

    @Override
    public void updateFrame() {
        if (Math.abs(xSpeed) <= Math.abs(ySpeed) && ySpeed <= 0) {
            side = E_DLRU_UP;
        }else if (Math.abs(xSpeed) <= Math.abs(ySpeed) && ySpeed > 0) {
            side = E_DLRU_DOWN;
        }else if (Math.abs(xSpeed) >= Math.abs(ySpeed) && xSpeed >= 0) {
            side = E_DLRU_RIGHT;
        }else if (Math.abs(xSpeed) >= Math.abs(ySpeed) && xSpeed < 0) {
            side = E_DLRU_LEFT;
        }
        if ( ++n == E_GROUND_MIAU_SPRITE_TICKSxFRAME) {
            n = 0;
            if (xSpeed != 0 || ySpeed != 0){
                currentFrame = ++currentFrame%cols;
            }else {
                currentFrame = 0;
            }
        }
    }

}
