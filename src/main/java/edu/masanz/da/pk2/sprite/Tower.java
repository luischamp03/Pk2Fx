package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.sprite.weaponry.BurstGun;
import edu.masanz.da.pk2.sprite.weaponry.SnipperGun;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class Tower extends AEnemy{

    public Tower() {
        super(E_TOWER_SPRITE_IMAGE, E_TOWER_SPRITE_ROWS, E_TOWER_SPRITE_COLS);
        currentFrame = (int) (Math.random()*cols);
        setWeapon(new SnipperGun());
    }

    @Override
    public void update() {
        updateFrame();
    }

    public void updateFrame() {
        currentFrame = ++currentFrame%cols;
    }

}
