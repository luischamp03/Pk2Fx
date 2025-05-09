package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.game.GameObject;
import edu.masanz.da.pk2.game.SceneLoader;
import edu.masanz.da.pk2.sprite.weaponry.AShot;
import edu.masanz.da.pk2.sprite.weaponry.Cannonball;

import java.util.ArrayList;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class ProximityTower extends Tower {
    private int currentCooldown = 0;

    public ProximityTower() {
        this.img = E_PROXIMITY_TOWER_SPRITE_IMAGE;
        this.cols = E_PROXIMITY_TOWER_SPRITE_COLS;
        this.rows = E_PROXIMITY_TOWER_SPRITE_ROWS;
    }

    @Override
    public void update() {
        super.update();
        if (currentCooldown > 0) {
            currentCooldown--;
        }
    }

    @Override
    public ArrayList<AShot> shoot() {
        if (currentCooldown > 0) {
            return new ArrayList<>();
        }

        ArrayList<AShot> lista = new ArrayList<>();
        AShot shot =  new Cannonball();
        shot.setPos(this.getRect().centerX(), this.getRect().bottom);
        Hero hero = GameObject.getInstance().getHero();
        shot.setSpeed(SHOT_SPEED, (int) Math.toDegrees(Math.atan2(shot.getY() - hero.getY(), hero.getX() - shot.getX())));
        lista.add(shot);

        currentCooldown = E_PROXIMITY_TOWER_SHOOT_COOLDOWN;
        return lista;
    }

    public boolean isHeroInRange() {
        Hero hero = GameObject.getInstance().getHero();

        int heroX = (int) (hero.getRect().centerX() / SceneLoader.getTileWidth());
        int heroY = (int) (hero.getRect().centerY() / SceneLoader.getTileHeight());
        int towerX = (int) (this.getRect().centerX() / SceneLoader.getTileWidth());
        int towerY = (int) (this.getRect().centerY() / SceneLoader.getTileHeight());

        return Math.abs(heroX - towerX) + Math.abs(heroY - towerY) <= E_PROXIMITY_TOWER_RANGE;
    }
}