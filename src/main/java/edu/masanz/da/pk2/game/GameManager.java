package edu.masanz.da.pk2.game;

import edu.masanz.da.pk2.sprite.*;
import edu.masanz.da.pk2.sprite.weaponry.AShot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.masanz.da.pk2.game.enums.EAppStatus;
import edu.masanz.da.pk2.sprite.AEnemy;
import edu.masanz.da.pk2.sprite.IHaveShield;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class GameManager {

    // region attributes
    private Hero hero;
    protected LifesSprite lifesSprite;
    protected List<AShot> heroShots = new ArrayList<>();
    protected List<AShot> enemyShots = new ArrayList<>();
    protected List<AEnemy> enemies = new ArrayList<>();
    protected List<AItem> items = new ArrayList<>();
    protected List<SpriteTemp> temps = new ArrayList<>();

    public int score = 0;
    private AppStatus appStatus;

    private static Logger log = Logger.getLogger(GameManager.class);

    // endregion

    public GameManager(AppStatus appStatus) {
        PropertyConfigurator.configure(LOG4J_PROPERTIES);

        this.appStatus = appStatus;
        hero = new Hero();
        GameObject.getInstance().setHero(hero);
        lifesSprite = new LifesSprite(LIFES_SPRITE_IMAGE, appStatus);
    }

    public void start() {
        appStatus.start();
        log.debug("start level=" + appStatus.getLevel());
        score = 0;
        SceneLoader.loadScene(appStatus.getLevel());
        clearLevel();
        hero.setAlive(true);
        lifesSprite.update();
        enemies = SceneLoader.createEnemies();
        items = SceneLoader.createItems();
        log.debug("enemies=" + enemies.size() + " items=" + items.size());
    }

    public void nextLevel() {
        appStatus.nextLevel();
        log.debug("nextLevel level=" + appStatus.getLevel());
        SceneLoader.loadScene(appStatus.getLevel());
        clearLevel();
        hero.setAlive(true);
        enemies = SceneLoader.createEnemies();
        items = SceneLoader.createItems();
        log.debug("enemies=" + enemies.size() + " items=" + items.size());
    }

    public void sameLevel() {
        appStatus.sameLevel();
        log.debug("sameLevel level=" + appStatus.getLevel());
        clearLevel();
        lifesSprite.update();
        hero.setAlive(true);
    }

    private void clearLevel() {
        temps.clear();
        heroShots.clear();
        enemyShots.clear();
        hero.setInitPos();
    }

    public void finish() {
        appStatus.finish();
    }

    public int getLevel() {
        return appStatus.getLevel();
    }

    public Hero getHero() {
        return hero;
    }

    public void shot() {
        AShot shot = hero.shoot();
        if (shot != null) {
            heroShots.add(shot);
        }
    }

    public void updateGame() {
        //Detección de colisión entre balas
        for (Iterator<AShot> itHeroShot = heroShots.iterator(); itHeroShot.hasNext(); ) {
            AShot heroShot = itHeroShot.next();
            for (Iterator<AShot> enemyShots = this.enemyShots.iterator(); enemyShots.hasNext(); ) {
                AShot enemyShot = enemyShots.next();
                if (heroShot.collides(enemyShot)) {
                    itHeroShot.remove();
                    enemyShots.remove();
                    temps.add(new SpriteTemp(temps, enemyShot.getRect().centerX(), enemyShot.getRect().centerY(),
                            EXPLOSION_12_SPRITE_IMAGE, 12));
                    break;
                }
            }
        }

        //Detección de colisiones de los enemigos con los disparos del protagonista
        for (Iterator<AShot> itBullet = heroShots.iterator(); itBullet.hasNext(); ) {
            AShot heroShot = itBullet.next();
            List<AEnemy> newGhosts = new ArrayList<>();
            for (Iterator<AEnemy> itSprite = enemies.iterator(); itSprite.hasNext(); ) {
                ASprite sprite = itSprite.next();
                if (heroShot.collides(sprite)) {
                    if (sprite instanceof IHaveShield) {
                        temps.add(new SpriteTemp(temps, sprite.getRect().centerX(), sprite.getRect().centerY(),
                                EXPLOSION_9_SPRITE_IMAGE, 9));
                    } else {
                        if (sprite instanceof Creeper) {
                            enemyShots.addAll(((Creeper) sprite).shoot());
                        } else if (sprite instanceof Ghost) {
                            newGhosts.addAll(
                                    ((Ghost) sprite).spawn()
                            );
                        }
                        temps.add(new SpriteTemp(temps, sprite.getRect().centerX(), sprite.getRect().centerY(),
                                EXPLOSION_9_SPRITE_IMAGE, 9));
                        itSprite.remove();
                    }
                    score += PTS_ENEMYSHIP;
                    itBullet.remove();
                    break;
                }
            }
            enemies.addAll(newGhosts);
            if (GameArea.rect.contains(heroShot.getRect())) {
                heroShot.update();
            } else {
                itBullet.remove();
            }
        }

        //Actualización del protagonista
        if (hero.isAlive()) {
            hero.update();
        }
        //Actualización de los sprites temporales
        for (int i = temps.size() - 1; i >= 0; i--) {
            temps.get(i).update();
        }
        //Actualización de los enemigos
        for (ASprite enemy : enemies) {
            enemy.update();
        }
        //Actualización de los items
        for (AItem items : items) {
            items.update();
        }

        //Generación de nuevos enemigos
        /*List<AEnemy> newList = new ArrayList<>();
        for (ASprite enemy : enemies) {
            if (enemy instanceof ICanSpawn){
                newList.addAll(
                    ((ICanSpawn) enemy).spawn()
                );
            }
        }
        enemies.addAll(newList);*/
        //Comprobación del estado de la partida
        if (!hero.isAlive() && (temps.isEmpty())) {
            appStatus.oneLessLife();
            if (appStatus.getValue() == EAppStatus.E_APP_LOST) {
                log.debug("updateGame E_APP_LOST enemies=" + enemies.size() + " enemyShots=" + enemyShots.size() + " heroShots=" + heroShots.size());
            } else {
                log.debug("updateGame E_APP_ONELESSLIFE enemies=" + enemies.size() + " enemyShots=" + enemyShots.size() + " heroShots=" + heroShots.size());
            }
            return;
        }
        //Comprobación del estado de la partida
        if (hero.isAtFinishPosition()) {
            appStatus.newLevel();
            score += PTS_NEWLEVEL;
            return;
        }

        // Comprobacion si el heroe colisiona con la lava
        if (hero.isCollisionOnLava()){
            if(hero.isAlive()){
                hero.setAlive(false);
                temps.add(new SpriteTemp(temps, hero.getRect().centerX(), hero.getRect().centerY(),
                        EXPLOSION_12_SPRITE_IMAGE, 12));
            }
        }

        //Detección si los disparos de los enemigos han impactado con el protagonista
        //o se han salido del área se juego
        for (Iterator<AShot> itShot = enemyShots.iterator(); itShot.hasNext(); ) {
            AShot AShot = itShot.next();
            if (hero.isAlive() && AShot.collides(hero)) {
                hero.setAlive(false);
                temps.add(new SpriteTemp(temps, hero.getRect().centerX(), hero.getRect().centerY(),
                        EXPLOSION_12_SPRITE_IMAGE, 12));
            } else {
                if (GameArea.rect.contains(AShot.getRect())) {
                    AShot.update();
                } else {
                    itShot.remove();
                }
            }
        }
        //Detección si los enemigos han impactado con el protagonista
        //o se han salido del área se juego
        for (Iterator<AEnemy> itSprite = enemies.iterator(); itSprite.hasNext(); ) {
            ASprite sprite = itSprite.next();
            if (hero.isAlive() && sprite.collides(hero)) {
                hero.setAlive(false);
                temps.add(new SpriteTemp(temps, hero.getRect().centerX(), hero.getRect().centerY(),
                        EXPLOSION_12_SPRITE_IMAGE, 12));
            }
            if (GameArea.rect.bottom < sprite.getRect().top - sprite.getRect().height()) {
                itSprite.remove();
            }
        }

        //Deteccion si los items han impactado con el protagonista
        for (Iterator<AItem> itSprite = items.iterator(); itSprite.hasNext(); ) {
            ASprite sprite = itSprite.next();
            if (hero.isAlive() && sprite.collides(hero)) {
                if (sprite instanceof LifePotion) {
                    if (appStatus.getLifes() != INIT_LIFES) {
                        int lifes = appStatus.getLifes() + 1;
                        appStatus.setLifes(lifes);
                    }
                    log.debug("lifes="+appStatus.getLifes());
                    lifesSprite.update();
                } else if (sprite instanceof Treasure) {
                    score += 50;
                    log.debug("score="+score);
                } else if(sprite instanceof Portal){
                    Portal portal = (Portal) sprite;
                    if (portal.canTeleport() && portal.getDestinationPortal() != null) {
                        Portal portalDestino = portal.getDestinationPortal();
                        hero.setX(portalDestino.getX());
                        hero.setY(portalDestino.getY());
                        // Se activa cooldown en ambos portales para evitar que el heroe sea teletransportado inmediatamente despues de cruzar
                        portal.activateCooldown();
                        portalDestino.activateCooldown();
                    }
                    break;
                }
                itSprite.remove();
            }
        }

        //Generación de disparos por parte de los enemigos
        ArrayList<AEnemy> shooterEnemies = new ArrayList<>();
        for (AEnemy enemy : enemies) {
            if (enemy.hasWeapon()) {
                if (enemy instanceof ProximityTower) {
                    ProximityTower tower = (ProximityTower) enemy;
                    // Solo dispara si el héroe está en rango
                    if (tower.isHeroInRange()) {
                        enemyShots.addAll(tower.shoot());
                    }
                } else {
                    // Otros enemigos siguen el balance de disparos
                    if (!(enemy instanceof Creeper)) {
                        shooterEnemies.add(enemy);
                    }
                }
            }
        }

        // Balance de disparos para enemigos normales
        if (!shooterEnemies.isEmpty()) {
            int n = heroShots.size() - enemyShots.size();
            for (int i = 0; i < n; i++) {
                int j = (int) (Math.random() * shooterEnemies.size());
                enemyShots.addAll(shooterEnemies.get(j).shoot());
            }
        }
    }

}
