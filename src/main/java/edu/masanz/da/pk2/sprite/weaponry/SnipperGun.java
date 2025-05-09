package edu.masanz.da.pk2.sprite.weaponry;

import edu.masanz.da.pk2.game.GameObject;
import edu.masanz.da.pk2.sprite.ASprite;
import edu.masanz.da.pk2.sprite.Hero;

import java.util.ArrayList;

import static edu.masanz.da.pk2.game.AppConsts.SHOT_SPEED;

public class SnipperGun extends AWeapon {

    @Override
    public ArrayList<AShot> shoot(ASprite sprite) {
        ArrayList<AShot> list = new ArrayList<>();
        AShot shot = new Cannonball();
        shot.setPos(sprite.getRect().centerX(), sprite.getRect().bottom);
        Hero hero = GameObject.getInstance().getHero();
        shot.setSpeed(SHOT_SPEED, (int) Math.toDegrees(Math.atan2(shot.getY() - hero.getY(), hero.getX() - shot.getX())));
        list.add(shot);
        return list;
    }

}
