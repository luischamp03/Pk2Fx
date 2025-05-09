package edu.masanz.da.pk2.sprite.weaponry;

import edu.masanz.da.pk2.game.GameObject;
import edu.masanz.da.pk2.sprite.ASprite;
import edu.masanz.da.pk2.sprite.Hero;

import java.util.ArrayList;

import static edu.masanz.da.pk2.game.AppConsts.SHOT_SPEED;

public class BurstGun extends AWeapon {

    @Override
    public ArrayList<AShot> shoot(ASprite sprite) {
        ArrayList<AShot> list = new ArrayList<>();
        AShot shot1 = new Cannonball();
        AShot shot2 = new Cannonball();
        AShot shot3 = new Cannonball();
        AShot shot4 = new Cannonball();

        shot1.setPos(sprite.getRect().centerX(), sprite.getRect().bottom);
        shot2.setPos(sprite.getRect().centerX(), sprite.getRect().top);
        shot3.setPos(sprite.getRect().left, sprite.getRect().centerY());
        shot4.setPos(sprite.getRect().right, sprite.getRect().centerY());

        shot1.setSpeed(SHOT_SPEED,270);
        shot2.setSpeed(SHOT_SPEED,90);
        shot3.setSpeed(SHOT_SPEED,180);
        shot4.setSpeed(SHOT_SPEED,0);

        list.add(shot1);
        list.add(shot2);
        list.add(shot3);
        list.add(shot4);
        return list;
    }
}
