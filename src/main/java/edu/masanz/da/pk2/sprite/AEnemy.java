package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.sprite.weaponry.AShot;
import edu.masanz.da.pk2.sprite.weaponry.AWeapon;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Los enemigos van a poder disparar si tienen un arma
 */
public abstract class AEnemy extends ASprite implements ICanShoot {

    private AWeapon weapon;

    public AEnemy(Image img, int rows, int cols) {
        super(img, rows, cols);
    }

    public AEnemy(Image img, int rows, int cols, int rowSpace, int colSpace) {
        super(img, rows, cols, rowSpace, colSpace);
    }

    public void setWeapon(AWeapon weapon) {
        this.weapon = weapon;
    }

    public boolean hasWeapon(){
        return weapon != null;
    }

    public ArrayList<AShot> shoot(){
        ArrayList<AShot> list = new ArrayList<>();
        if (weapon!=null) {
            list = weapon.shoot(this);
        }
        return list;
    }

}
