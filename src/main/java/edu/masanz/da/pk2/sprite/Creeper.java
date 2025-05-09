package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.sprite.weaponry.BurstGun;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class Creeper extends AGroundEnemy{

    private int n = 0;

    public Creeper() {
        super(E_CREEPER_SPRITE_IMAGE, E_CREEPER_SPRITE_ROWS, E_CREEPER_SPRITE_COLS);
        setSpeed(E_CREEPER_SPEED, 0);
        setWeapon(new BurstGun());
    }

    public Creeper(int angleDegrees) {
        this();
        setSpeed(E_CREEPER_SPEED, angleDegrees);
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
        if ( ++n == 10) {
            n = 0;
            if (xSpeed != 0 || ySpeed != 0){
                currentFrame = ++currentFrame%cols;
            }else {
                currentFrame = 0;
            }
        }
    }
}
