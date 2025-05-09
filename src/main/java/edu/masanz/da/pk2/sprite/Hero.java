package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.game.GameArea;
import edu.masanz.da.pk2.game.SceneLoader;
import edu.masanz.da.pk2.sprite.weaponry.AShot;
import edu.masanz.da.pk2.sprite.weaponry.Shot;
import edu.masanz.da.pk2.util.Rect;

import static edu.masanz.da.pk2.game.AppConsts.*;

public class Hero extends ASprite {

    private boolean isAlive;

    public Hero() {
        super(HERO_SPRITE_IMAGE, HERO_SPRITE_ROWS, HERO_SPRITE_COLS, HERO_SPRITE_ROWSPACE, HERO_SPRITE_COLSPACE);
        xSpeed = HERO_MAX_SPEED;
        ySpeed = HERO_MAX_SPEED;
        setInitPos();
        isAlive = true;
    }

    public void setInitPos(){
        x = (int) (SceneLoader.getHeroBoardPosIni().getX() + SceneLoader.getTileWidth()/2 - width/2);
        y = (int) (SceneLoader.getHeroBoardPosIni().getY() + SceneLoader.getTileHeight());
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public void moveRight(){
        if (xSpeed != HERO_MAX_SPEED){
            xSpeed++;
        }
        side = HERO_RIGHT;
    }

    public void moveLeft(){
        if (xSpeed != -HERO_MAX_SPEED){
            xSpeed--;
        }
        side = HERO_LEFT;
    }

    public void moveUp(){
        if (ySpeed != -HERO_MAX_SPEED){
            ySpeed--;
        }
        side = HERO_UP;
    }

    public void moveDown(){
        if (ySpeed != HERO_MAX_SPEED){
            ySpeed++;
        }
        side = HERO_DOWN;
    }

    public void waiting(){      //moveNoWhere
        xSpeed = 0;
        ySpeed = 0;
        currentFrame = 0;
    }

    public void stopVert(){
        ySpeed = 0;
    }

    public void stopHoriz(){
        xSpeed = 0;
    }

    public void updateFrame(){
        if (xSpeed != 0 || ySpeed != 0){
            currentFrame = ++currentFrame%cols;
        }else {
            currentFrame = 0;
        }
    }

    @Override
    public Rect[] getRects(){
        Rect[] rects;
        if (xSpeed==0){
            rects = new Rect[3];
            rects[0] = new Rect((int)(x+(0.47*width)),y,(int)(x+(0.53*width)),y+height);
            rects[1] = new Rect((int)(x+(0.37*width)),(int)(y+(0.22*height)),(int)(x+(0.63*width)),y+height);
            rects[2] = new Rect(x,(int)(y+(0.4*height)),x+width,(int)(y+(0.55*height)));
        }else{
            rects = new Rect[2];
            rects[0] = new Rect(x+width/3,y,x+2*width/3,y+height);
            rects[1] = new Rect((int)(x+(0.3*width)),(int)(y+(0.4*height)),(int)(x+(0.7*width)),y+height);
        }
        return rects;
    }

    @Override
    public void update() {
        int nextX = x;
        int nextY = y;
        // check if the next position is inside the gameRect
        if (x <= GameArea.rect.right - width - xSpeed && x >= GameArea.rect.left - xSpeed ) {
            nextX = x + xSpeed;
        }
        if (y <= GameArea.rect.bottom - height - ySpeed && y >= GameArea.rect.top - ySpeed ){
            nextY = y + ySpeed;
        }
        // check if the next position is inside a path, this is '0' value in the map
        // as two axis are checked, the sprite can move diagonally if possible,
        // or in one axis if the other is blocked, this is good for bending the corners
        // +width/2 is used to check the horizontal center of the sprite, more realistic
        // -height/4 is used to check the feet of the sprite due to zenital view
        if (SceneLoader.getPosValue(nextX+width/2, nextY-height/4) <= SL_V_FLOOR){
            x = nextX;
            y = nextY;
        }else if (SceneLoader.getPosValue(x+width/2, nextY-height/4) <= SL_V_FLOOR){
            y = nextY;
        }else if (SceneLoader.getPosValue(nextX+width/2, y-height/4) <= SL_V_FLOOR){
            x = nextX;
        }
    }

    public boolean isAtFinishPosition(){
        return SceneLoader.getPosValue(x+width/2, y-height/3) == SL_V_FIN;
    }

    public AShot shoot() {
        AShot shot = null;
        // if the sprite is not moving, the shot is created depending on the side the hero is facing
        if (xSpeed == 0 && ySpeed == 0) {
            switch (side) {
                case HERO_DOWN:
                    shot = new Shot(HEROSHOT_SPRITE_IMAGE, 0, -SHOT_SPEED);
                    shot.setPos(x + width/2 - shot.getRect().width()/2, y + height/2);
                    break;
                case HERO_UP:
                    shot = new Shot(HEROSHOT_SPRITE_IMAGE, 0, SHOT_SPEED);
                    shot.setPos(x + width/2 - shot.getRect().width()/2, y - shot.getRect().height());
                    break;
                case HERO_LEFT:
                    shot = new Shot(HEROSHOT_SPRITE_IMAGE, -SHOT_SPEED, 0);
                    shot.setPos(x, y + height/2 - shot.getRect().height()/2);
                    break;
                case HERO_RIGHT:
                    shot = new Shot(HEROSHOT_SPRITE_IMAGE, SHOT_SPEED, 0);
                    shot.setPos(x + width/2, y + height/2 - shot.getRect().height()/2);
                    break;
            }
        }
        // if the sprite is moving, the shot is created depending on the direction of the movement
        else {
            shot = new Shot(HEROSHOT_SPRITE_IMAGE, xSpeed, -ySpeed);
            switch (side) {
                case HERO_DOWN:
                    shot.setPos(x + width/2 - shot.getRect().width()/2, y + height/2);
                    break;
                case HERO_UP:
                    shot.setPos(x + width/2 - shot.getRect().width()/2, y - shot.getRect().height());
                    break;
                case HERO_LEFT:
                    shot.setPos(x, y + height/2 - shot.getRect().height()/2);
                    break;
                case HERO_RIGHT:
                    shot.setPos(x + width/2, y + height/2 - shot.getRect().height()/2);
                    break;
            }
        }
        return shot;
    }

    public boolean isCollisionOnLava() {
        int nextX = x;
        int nextY = y;
        if (x <= GameArea.rect.right - width - xSpeed && x >= GameArea.rect.left - xSpeed ) {
            nextX = x + xSpeed;
        }
        if (y <= GameArea.rect.bottom - height - ySpeed && y >= GameArea.rect.top - ySpeed ){
            nextY = y + ySpeed;
        }

        if (SceneLoader.getPosValue(nextX+width/2, nextY-height/4) >= SL_V_LAVA){
            return true;
        }else if (SceneLoader.getPosValue(x+width/2, nextY-height/4) >= SL_V_LAVA){
            return true;
        }else if (SceneLoader.getPosValue(nextX+width/2, y-height/4) >= SL_V_LAVA){
            return true;
        }

        return false;
    }
}
