package edu.masanz.da.pk2.sprite;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class LifePotion extends AItem {

    public LifePotion() {
        super(I_LIFE_POTION_SPRITE_IMAGE, I_LIFE_POTION_SPRITE_ROWS, I_LIFE_POTION_SPRITE_COLS);
        currentFrame = (int) (Math.random()*cols);
    }

    @Override
    public void update() {
        updateFrame();
    }

    public void updateFrame() {
        if (++n == I_LIFE_POTION_SPRITE_TICKSxFRAME) {
            n = 0;
            currentFrame = ++currentFrame%cols;
        }
    }
}
