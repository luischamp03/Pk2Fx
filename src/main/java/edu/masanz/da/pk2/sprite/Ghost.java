package edu.masanz.da.pk2.sprite;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class Ghost extends AFlyingEnemy implements ICanSpawn{

    private static Logger log = Logger.getLogger(Ghost.class);

    public Ghost() {
        super(E_GHOST_SPRITE_IMAGE, E_GHOST_SPRITE_ROWS, E_GHOST_SPRITE_COLS);
        setSpeed(E_GHOST_SPEED, 0);
    }

    public Ghost(int angleDegrees) {
        this();
        setSpeed(E_GHOST_SPEED, angleDegrees);
    }

    @Override
    public List<AEnemy> spawn() {
        List<AEnemy> newGhosts = new ArrayList<>();
        boolean spawn = false;

        if(Math.random() <= PROB_SPAWN_ENEMIES){
            spawn = true;
        }

        if (spawn){
            AEnemy ghost1 = new Ghost(60);
            AEnemy ghost2 = new Ghost(240);

            ghost1.setX(this.x);
            ghost1.setY(this.y);
            ghost2.setX(this.x);
            ghost2.setY(this.y);

            newGhosts.add(ghost1);
            newGhosts.add(ghost2);
        }

        log.debug("Ghost spawned: " + newGhosts.size());

        return newGhosts;
    }
}
