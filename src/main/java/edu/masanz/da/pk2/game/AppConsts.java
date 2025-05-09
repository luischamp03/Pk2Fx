package edu.masanz.da.pk2.game;

import edu.masanz.da.pk2.Main;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;

public final class AppConsts {

    //region Logs
    public static final URL LOG4J_PROPERTIES = Main.class.getResource("log4j.properties");
    public static final String SYSTEM_PROPERTY = "userApp.userName";
    //endregion

    //region AppPk2Fx
    public static final String APP_TITLE = "PK2";
    public static final int HUD_HEIGHT = 34;
    public static final int BOARD_WIDTH = 240*2;//16*30=480
    public static final int BOARD_HEIGHT = 337*2 - HUD_HEIGHT;//16*40=640
    public static final double GAME_SPEED = 30;//steps per second
    public static final Image ICON_16 = new Image(Main.class.getResource("icon/icon16.png").toString());
    public static final Image ICON_32 = new Image(Main.class.getResource("icon/icon32.png").toString());
    public static final Image ICON_64 = new Image(Main.class.getResource("icon/icon64.png").toString());
    public static final Image ICON_128 = new Image(Main.class.getResource("icon/icon128.png").toString());
    //endregion

    //region GameManager
    public static final int INIT_LEVEL = 7;         //1;     <------      write here the level you want to start
    public static final int INIT_LIFES = 3;
    public static final int PTS_ENEMYSHIP = 20;
    public static final int PTS_NEWLEVEL = 100;
    public static final Image HERO_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/hero.png").toString());
    public static final int HERO_SPRITE_COLS = 5;
    public static final int HERO_SPRITE_WIDTH = 24;
    public static final int HERO_SPRITE_COLSPACE = 8;
    public static final int HERO_SPRITE_ROWS = 4;
    public static final int HERO_SPRITE_HEIGHT = 32;
    public static final int HERO_SPRITE_ROWSPACE = 0;
    public static final int SHOT_SPEED = 10;
    public static final Image LIFES_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/lifes.png").toString());
    public static final Image HEROSHOT_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/ball.png").toString());
    public static final Image EXPLOSION_9_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/explo9.png").toString());
    public static final Image EXPLOSION_12_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/explo12.png").toString());
    //endregion

    //region SceneLoader
    public static final String SL_PATH = "map/";
    public static final String SL_MAP = "surface";
    public static final String SL_ELE = "element";
    public static final String SL_EXT = ".txt";
    public static final char SL_V_PATH = '0';
    public static final char SL_V_INI = '1';
    public static final char SL_V_FIN = '2';
    public static final char SL_V_FLOOR = '2';
    public static final char SL_V_WATER = '3';
    public static final char SL_V_WALL = '4';
    public static final char SL_V_LAVA = '5';
    public static final String SL_ENEMIES = "FfGgHhMmBKkRrTPEeOo";
    public static final String SL_ITEMS = "LCX"; // L -> pocion de vida, C -> tesoro, X -> portal
    //endregion

    //region Enemies
    public static final int E_DLRU_DOWN = 0;
    public static final int E_DLRU_LEFT = 1;
    public static final int E_DLRU_RIGHT = 2;
    public static final int E_DLRU_UP = 3;
    //endregion

    //region CREEPER
    public static final int SL_E_CREEPER_A = 'E';
    public static final int SL_E_CREEPER_B = 'e';
    public static final int E_CREEPER_SPEED = 5;
    public static final Image E_CREEPER_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/creeper.png").toString());
    public static final int E_CREEPER_SPRITE_COLS = 3;
    public static final int E_CREEPER_SPRITE_ROWS = 4;
    //endregion

    //region GHOST
    public static final int SL_E_GHOST_A = 'O';
    public static final int SL_E_GHOST_B = 'o';
    public static final int E_GHOST_SPEED = 5;
    public static final double PROB_SPAWN_ENEMIES = 0.3;
    public static final Image E_GHOST_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/ghost.png").toString());
    public static final int E_GHOST_SPRITE_COLS = 5;
    public static final int E_GHOST_SPRITE_ROWS = 4;
    //endregion

    //region FLYING_BUNNY
    public static final char SL_E_FLYING_BUNNY_R = 'F';
    public static final char SL_E_FLYING_BUNNY_L = 'f';
    public static final char SL_E_FLYING_BUNNY_RD = 'G';
    public static final char SL_E_FLYING_BUNNY_LD = 'g';
    public static final char SL_E_FLYING_BUNNY_RU = 'H';
    public static final char SL_E_FLYING_BUNNY_LU = 'h';
    public static final int E_FLYING_BUNNY_SPEED = 5;
    public static final Image E_FLYING_BUNNY_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/flyingbunny.png").toString());
    public static final int E_FLYING_BUNNY_SPRITE_ROWS = 4;
    public static final int E_FLYING_BUNNY_SPRITE_COLS = 6;
    //endregion

    //region GROUND_MEOWTH
    public static final char SL_E_GROUND_MIAU_V = 'M';
    public static final char SL_E_GROUND_MIAU_H = 'm';
    public static final int E_GROUND_MIAU_SPEED = 5;
    public static final Image E_GROUND_MIAU_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/miau.png").toString());
    public static final int E_GROUND_MIAU_SPRITE_ROWS = 4;
    public static final int E_GROUND_MIAU_SPRITE_COLS = 4;
    public static final int E_GROUND_MIAU_SPRITE_ROWSPACE = 4;
    public static final int E_GROUND_MIAU_SPRITE_COLSPACE = 0;
    public static final int E_GROUND_MIAU_SPRITE_TICKSxFRAME = 2;
    //endregion

    //region GROUND_BEAR
    public static final char SL_E_GROUND_BEAR = 'B';
    public static final int E_GROUND_BEAR_SPEED = 3;
    public static final Image E_GROUND_BEAR_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/bear.png").toString());
    public static final int E_GROUND_BEAR_SPRITE_ROWS = 4;
    public static final int E_GROUND_BEAR_SPRITE_COLS = 3;
    public static final int E_GROUND_BEAR_SPRITE_ROWSPACE = 0;
    public static final int E_GROUND_BEAR_SPRITE_COLSPACE = 0;
    public static final int E_GROUND_BEAR_SPRITE_TICKSxFRAME = 3;
    public static final int E_GROUND_BEAR_SPRITE_TICKSxREDIRECTION = 3*30;
    //endregion

    //region WATER_SHARK
    public static final char SL_E_WATER_SHARK_U = 'K';
    public static final char SL_E_WATER_SHARK_D = 'k';
    public static final char SL_E_WATER_SHARK_R = 'R';
    public static final char SL_E_WATER_SHARK_L = 'r';
    public static final int E_WATER_SHARK_SPEED = 2;
    public static final Image E_WATER_SHARK_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/sharky.png").toString());
    public static final int E_WATER_SHARK_SPRITE_ROWS = 4;
    public static final int E_WATER_SHARK_SPRITE_COLS = 4;
    public static final int E_WATER_SHARK_SPRITE_ROWSPACE = 3;
    public static final int E_WATER_SHARK_SPRITE_COLSPACE = 2;
    //endregion

    //region TOWER
    public static final char SL_E_TOWER = 'T';
    public static final int E_TOWER_SPEED = 0;
    public static final Image E_TOWER_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/pokeball42.png").toString());
    public static final int E_TOWER_SPRITE_ROWS = 1;
    public static final int E_TOWER_SPRITE_COLS = 12;
    //endregion

    //region PROXIMITY TOWER
    public static final char SL_E_PROXIMITY_TOWER = 'P';
    public static final int E_PROXIMITY_TOWER_RANGE = 6;
    public static final int E_PROXIMITY_TOWER_SHOOT_COOLDOWN = 30;
    public static final Image E_PROXIMITY_TOWER_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/proximitytower.png").toString());
    public static final int E_PROXIMITY_TOWER_SPRITE_COLS = 1;
    public static final int E_PROXIMITY_TOWER_SPRITE_ROWS = 1;
    //endregion

    //region LIFE POTION
    public static final char SL_I_LIFE_POTION = 'L';
    public static final int I_LIFE_POTION_SPEED = 0;
    public static final Image I_LIFE_POTION_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/lifepotion.png").toString());
    public static final int I_LIFE_POTION_SPRITE_COLS = 8;
    public static final int I_LIFE_POTION_SPRITE_ROWS = 1;
    public static final int I_LIFE_POTION_SPRITE_TICKSxFRAME = 2;
    //endregion

    // region TREASURE
    public static final char SL_I_TREASURE = 'C';
    public static final int I_TREASURE_SPEED = 0;
    public static final Image I_TREASURE_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/treasure.png").toString());
    public static final int I_TREASURE_SPRITE_COLS = 11;
    public static final int I_TREASURE_SPRITE_ROWS = 1;
    public static final int I_TREASURE_SPRITE_TICKSxFRAME = 2;
    // endregion

    //region PORTAL
    public static final char SL_I_PORTAL = 'X';
    public static final int I_PORTAL_SPEED = 0;
    public static final int I_PORTAL_COOLDOWN = 30;
    public static final Image I_PORTAL_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/portal.png").toString());
    public static final int I_PORTAL_SPRITE_COLS = 6;
    public static final int I_PORTAL_SPRITE_ROWS = 1;
    public static final int I_PORTAL_SPRITE_TICKSxFRAME = 3;

    // endregion

    //region Renderer
    public static final double MARGIN_LEFT = 10.0;
    public static final double MARGIN_TOP = 22.0;//see BOARD_MARGIN_TOP
    public static final String TXT_YOU_CAN = "Tú puedes";
    public static final String TXT_PRESS_ENTER = "Pulsa ENTER";
    public static final String TXT_TO_START = "para empezar";
    public static final String TXT_TO_CONTINUE = "para continuar";
    public static final String TXT_YOU_WON = "GANASTE";
    public static final String TXT_YOU_LOST = "PERDISTE";
    public static final String TXT_LEVEL = "NIVEL";
    public static final String TXT_POINTS = "Puntos";
    public static final Image COVER_IMAGE = new Image(Main.class.getResource("img/PK2.jpeg").toString());
    public static final Font FONT_HELVETICA_16 = Font.font( "Helvetica", FontWeight.EXTRA_BOLD, 16 );
    public static final Font FONT_HELVETICA_52 = Font.font( "Helvetica", FontWeight.EXTRA_BOLD, 52 );
    //endregion

    //region Gun
    public static final Image BALL_SPRITE_IMAGE = new Image(Main.class.getResource("sprite/ball.png").toString());
    //endregion

    //region Hero
    public static final int HERO_DOWN = 0;
    public static final int HERO_LEFT = 1;
    public static final int HERO_UP = 2;
    public static final int HERO_RIGHT = 3;
    public static final int HERO_MAX_SPEED = 5;
    //endregion

    //region Lifes
    public static final int LIFES_ROWS = 4;
    public static final int LIFES_COLS = 1;
    public static final double LIFES_ALFA = 1;// 0.75;
    //endregion

    private AppConsts() { }

}
