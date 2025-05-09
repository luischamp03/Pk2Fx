package edu.masanz.da.pk2.game;

import edu.masanz.da.pk2.sprite.Hero;

public class GameObject {
    private static GameObject instance;
    private Hero hero;

    private GameObject() {}

    public static GameObject getInstance() {
        if (instance == null) {
            instance = new GameObject();
        }
        return instance;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Hero getHero() {
        return hero;
    }

}
