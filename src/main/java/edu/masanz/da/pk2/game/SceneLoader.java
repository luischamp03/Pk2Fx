package edu.masanz.da.pk2.game;

import edu.masanz.da.pk2.Main;
import edu.masanz.da.pk2.sprite.*;
import edu.masanz.da.pk2.util.Position;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class SceneLoader {

    private static int sceneNumber = 0;
    private static int sceneLimit = 0;
    private static char[][] m = null;
    private static int rows = 0;
    private static int cols = 0;

    private static int boardWidth = GameArea.rect.width();
    private static int boardHeight = GameArea.rect.height();
    private static double tileWidth = 16;//default
    private static double tileHeight = 16;//default
    private static Position heroCoordPosIni = new Position();
    private static Position heroCoordPosFin = new Position();
    private static Position heroBoardPosIni = new Position();
    private static Position heroBoardPosFin = new Position();


    private static void getSceneLimit() {
        String resourcePath = SL_PATH;
        String sceneFileName = SL_MAP;
        // How many files are in resourcePath that start with sceneFileName
        sceneLimit = 0;
        try {
            File file = new File(Main.class.getResource(resourcePath).toURI());
            for (File f : file.listFiles()) {
                if (f.getName().startsWith(sceneFileName)) {
                    sceneLimit++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static char[][] loadScene(int number) {
        if (sceneLimit == 0) {
            getSceneLimit();
        }
        if (number == sceneNumber) {
            return m;
        }
        sceneNumber = number;
        int level = sceneNumber % sceneLimit;
        if (level == 0) { level = sceneLimit; }
        List<String> lines = new ArrayList<>();
        String resourcePath = SL_PATH + SL_MAP + level + SL_EXT;
        InputStream inputStream = Main.class.getResourceAsStream(resourcePath);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            for (String line; (line = reader.readLine()) != null;) {
                line.trim();
                if (!line.isEmpty()) {
                    line = line.replaceAll("\\s+", "");
                    lines.add(line);
                    int x = line.indexOf(SL_V_INI);
                    if (x >= 0) {
                        int y = lines.size() - 1;
                        heroCoordPosIni = new Position(x, y);
                    }
                    x = line.indexOf(SL_V_FIN);
                    if (x >= 0) {
                        int y = lines.size() - 1;
                        heroCoordPosFin = new Position(x, y);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lines.isEmpty()) {
            return null;
        }
        String text0 = lines.get(0);
        rows = lines.size();
        cols = text0.length();
        m = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                m[i][j] = line.charAt(j);
            }
        }
        tileWidth = 1.0 * boardWidth / cols;
        tileHeight = 1.0 * boardHeight / rows;
        heroBoardPosIni.setX((int) (heroCoordPosIni.getX() * tileWidth));
        heroBoardPosIni.setY((int) (heroCoordPosIni.getY() * tileHeight));
        heroBoardPosFin.setX((int) (heroCoordPosFin.getX() * tileWidth));
        heroBoardPosFin.setY((int) (heroCoordPosFin.getY() * tileHeight));
        return m;
    }

    public static List<AEnemy> createEnemies() {
        List<AEnemy> enemies = new ArrayList<>();
        int level = sceneNumber % sceneLimit;
        if (level == 0) { level = sceneLimit; }
        String elementPath = SL_PATH + SL_ELE + level + SL_EXT;
        try {
            InputStream inputStream = Main.class.getResourceAsStream(elementPath);
            if (inputStream == null) { return enemies; }
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            int yIdx = 0;
            for (String line; (line = reader.readLine()) != null;) {
                line.trim();
                if (!line.isEmpty()) {
                    line = line.replaceAll("\\s+", "");
                    for (int xIdx = 0; xIdx < line.length(); xIdx++) {
                        char c = line.charAt(xIdx);
                        if ( SL_ENEMIES.indexOf(c) == -1) { continue; }
                        AEnemy enemy = switch (c) {
                            case SL_E_FLYING_BUNNY_R -> new FlyingBunny( 0);    // F
                            case SL_E_FLYING_BUNNY_L -> new FlyingBunny(180);   // f
                            case SL_E_FLYING_BUNNY_RD -> new FlyingBunny(330);  // G
                            case SL_E_FLYING_BUNNY_LD -> new FlyingBunny(210);  // g
                            case SL_E_FLYING_BUNNY_RU -> new FlyingBunny(30);   // H
                            case SL_E_FLYING_BUNNY_LU -> new FlyingBunny(150);  // h
                            case SL_E_GROUND_MIAU_V -> new Miau(60);        // M
                            case SL_E_GROUND_MIAU_H -> new Miau(30);        // m
                            case SL_E_GROUND_BEAR -> new Bear();                              // B
                            case SL_E_WATER_SHARK_U -> new Shark(90);           // K
                            case SL_E_WATER_SHARK_D -> new Shark(270);          // k
                            case SL_E_WATER_SHARK_R -> new Shark(0);            // R
                            case SL_E_WATER_SHARK_L -> new Shark(180);          // r
                            case SL_E_TOWER -> new Tower();                                   // T
                            case SL_E_PROXIMITY_TOWER -> new ProximityTower();              // P
                            case SL_E_CREEPER_A -> new Creeper(60);               //E
                            case SL_E_CREEPER_B -> new Creeper(240);               //e
                            case SL_E_GHOST_A -> new Ghost(60);                  //O
                            case SL_E_GHOST_B -> new Ghost(240);                 //o
                            default -> null;
                        };
                        if (enemy != null) {
                            enemy.setPos((int) (xIdx * tileWidth), (int) ((yIdx+1) * tileHeight));
                            enemies.add(enemy);
                        }
                    }
                }
                yIdx++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enemies;
    }

    // Metodo que crea los items en base al mapa
    public static List<AItem> createItems() {
        List<AItem> items = new ArrayList<>();
        List<Portal> portals = new ArrayList<>();
        int level = sceneNumber % sceneLimit;
        if (level == 0) { level = sceneLimit; }
        String elementPath = SL_PATH + SL_ELE + level + SL_EXT;
        try {
            InputStream inputStream = Main.class.getResourceAsStream(elementPath);
            if (inputStream == null) { return items; }
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            int yIdx = 0;
            for (String line; (line = reader.readLine()) != null;) {
                line.trim();
                if (!line.isEmpty()) {
                    line = line.replaceAll("\\s+", "");
                    for (int xIdx = 0; xIdx < line.length(); xIdx++) {
                        char c = line.charAt(xIdx);
                        if ( SL_ITEMS.indexOf(c) == -1) { continue; }
                        AItem item = switch (c) {
                            case SL_I_LIFE_POTION -> new LifePotion(); // L
                            case SL_I_TREASURE -> new Treasure(); // C
                            case SL_I_PORTAL -> { // X
                                Portal portal = new Portal();
                                portals.add(portal);
                                yield portal;
                            }
                            default -> null;
                        };
                        if (item != null) {
                            item.setPos((int) (xIdx * tileWidth), (int) ((yIdx+1) * tileHeight));
                            items.add(item);
                        }
                    }
                }
                yIdx++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Vincular los portales
        if (portals.size() == 2) {
            Portal primerPortal = portals.get(0);
            Portal segundoPortal = portals.get(1);
            primerPortal.setDestinationPortal(segundoPortal);
            segundoPortal.setDestinationPortal(primerPortal);
        }

        return items;
    }

    public static int getPosValue(int x, int y) {
        if (m == null) {
            return 0;
        }
        if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
            return 0;
        }
        int i = (y * m.length / boardHeight);
        if (i >= m.length) {
            i = m.length - 1;
        }
        int j = (x * m[0].length / boardWidth);
        if (j >= m[0].length) {
            j = m[0].length - 1;
        }
        return m[i][j];
    }

    public static Position getHeroBoardPosIni() {
        return heroBoardPosIni;
    }

    public static double getTileWidth() {
        return tileWidth;
    }

    public static double getTileHeight() {
        return tileHeight;
    }

    public static void main(String[] args) {
        loadScene(1);
        System.out.println(sceneLimit);
    }
}
