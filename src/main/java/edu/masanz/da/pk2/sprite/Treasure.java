package edu.masanz.da.pk2.sprite;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class Treasure extends AItem {

    public Treasure() {
        super(I_TREASURE_SPRITE_IMAGE, I_TREASURE_SPRITE_ROWS, I_TREASURE_SPRITE_COLS);
        currentFrame = (int) (Math.random()*cols);
    }

    @Override
    public void update() {
        updateFrame();
    }

    public void updateFrame() {
        if (++n == I_TREASURE_SPRITE_TICKSxFRAME) {
            n = 0;
            currentFrame = ++currentFrame%cols;
        }
    }
}
