package edu.masanz.da.pk2.sprite;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class Portal extends AItem{
    private Portal destinationPortal;
    private int currentCooldown = 0;

    public Portal() {
        super(I_PORTAL_SPRITE_IMAGE, I_PORTAL_SPRITE_ROWS, I_PORTAL_SPRITE_COLS);
        currentFrame = (int) (Math.random()*cols);
    }

    public Portal getDestinationPortal() {
        return destinationPortal;
    }

    public void setDestinationPortal(Portal destinationPortal) {
        this.destinationPortal = destinationPortal;
    }

    @Override
    public void update() {
        updateFrame();
        if (currentCooldown > 0) {
            currentCooldown--;
        }
    }

    public void updateFrame() {
        if (++n == I_PORTAL_SPRITE_TICKSxFRAME) {
            n = 0;
            currentFrame = ++currentFrame%cols;
        }
    }

    public boolean canTeleport() {
        return currentCooldown == 0;
    }

    public void activateCooldown() {
        currentCooldown = I_PORTAL_COOLDOWN;
    }

}
