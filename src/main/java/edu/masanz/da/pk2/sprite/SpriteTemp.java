package edu.masanz.da.pk2.sprite;

import javafx.scene.image.Image;

import java.util.List;

/**
 * Sprite temporal que tras N actualizaciones se autoelimina.
 */
public class SpriteTemp extends ASprite {
    private int life;
    private List<SpriteTemp> temps;

    public SpriteTemp(List<SpriteTemp> temps, int cx, int cy, Image img, int cols) {
        super(img,1,cols);
        x = cx - width/2;
        y = cy - height/2;
        this.temps = temps;
        life = cols;
    }

    public void update() {
        currentFrame = ++currentFrame % cols;
        if (--life < 1) {
            temps.remove(this);
        }
    }

    public void remove() {
        temps.remove(this);
    }

}
