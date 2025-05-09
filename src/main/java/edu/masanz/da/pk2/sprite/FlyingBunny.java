package edu.masanz.da.pk2.sprite;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class FlyingBunny extends AFlyingEnemy {

    public FlyingBunny() {
        super(E_FLYING_BUNNY_SPRITE_IMAGE, E_FLYING_BUNNY_SPRITE_ROWS, E_FLYING_BUNNY_SPRITE_COLS);
        setSpeed(E_FLYING_BUNNY_SPEED, 0);
    }

    public FlyingBunny(int angleDegrees) {
        this();
        setSpeed(E_FLYING_BUNNY_SPEED, angleDegrees);
    }

}
