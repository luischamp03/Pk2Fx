package edu.masanz.da.pk2.sprite;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class Shark extends AWaterEnemy {


    public Shark() {
        super(E_WATER_SHARK_SPRITE_IMAGE, E_WATER_SHARK_SPRITE_ROWS, E_WATER_SHARK_SPRITE_COLS,
                E_WATER_SHARK_SPRITE_ROWSPACE, E_WATER_SHARK_SPRITE_COLSPACE);
        setSpeed(E_WATER_SHARK_SPEED, 0);
    }

    public Shark(int angleDegrees) {
        this();
        setSpeed(E_WATER_SHARK_SPEED, angleDegrees);
    }

}
