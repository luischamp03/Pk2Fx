package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.game.GameObject;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class Bear extends AGroundEnemy {

    private final int N = E_GROUND_BEAR_SPRITE_TICKSxFRAME;
    private final int T = (int) (E_GROUND_BEAR_SPRITE_TICKSxREDIRECTION * (0.8 + 0.4*Math.random()) );
    private final int S = (int) (E_GROUND_BEAR_SPEED * (0.6 + 0.8*Math.random()) );
    private int n = 0;
    private int t = T - 1;


    public Bear() {
        super(E_GROUND_BEAR_SPRITE_IMAGE, E_GROUND_BEAR_SPRITE_ROWS, E_GROUND_BEAR_SPRITE_COLS,
                E_GROUND_BEAR_SPRITE_ROWSPACE, E_GROUND_BEAR_SPRITE_COLSPACE);
        setSpeed(E_GROUND_BEAR_SPEED, 0);
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
        if ( ++n == N ) {
            n = 0;
            if (xSpeed != 0 || ySpeed != 0){
                currentFrame = ++currentFrame%cols;
            }else {
                currentFrame = 0;
            }
        }
    }

    @Override
    public void update() {
        if ( ++t == T ) {
            t = 0;
            Hero hero = GameObject.getInstance().getHero();
            setSpeed(S, (int) Math.toDegrees(Math.atan2(y - hero.y, hero.x - x)));
        }
        super.update();
    }


}
