package edu.masanz.da.pk2.sprite;

import edu.masanz.da.pk2.util.Rect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.apache.log4j.Logger;

public abstract class ASprite {
    private static Logger log = Logger.getLogger(ASprite.class);

    // region attributes
    protected int rows;
    protected int cols;

    protected int colSpace;
    protected int rowSpace;

    protected int x;
    protected int y;

    protected int xSpeed;
    protected int ySpeed;
    protected Image img;

    protected int currentFrame;
    protected int width;
    protected int height;
    protected int side;
    // endregion

    public ASprite(Image img, int rows, int cols){
        this.img = img;
        this.rows = rows;
        this.cols = cols;
        this.side = 0;
        this.colSpace = 0;
        this.rowSpace = 0;
        if (img!=null) {
            this.width = (int) (img.getWidth() / cols);
            this.height = (int) (img.getHeight() / rows);
        }
    }

    public ASprite(Image img, int rows, int cols, int rowSpace, int colSpace){
        this.img = img;
        this.rows = rows;
        this.cols = cols;
        this.side = 0;
        this.colSpace = colSpace;
        this.rowSpace = rowSpace;
        if (img!=null) {
            this.width = (int) ( (img.getWidth() - (cols-1)*colSpace) / cols);
            this.height = (int) ( (img.getHeight() - (rows-1)*rowSpace) / rows);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
//        log.debug(getClass().getSimpleName() + " ("+ x + "," + y +") speed=" + String.format("%.2f", getSpeed()) + " angdeg=" + String.format("%.2f", getAngDeg()) );
    }


    public int getXSpeed(){
        return xSpeed;
    }

    public void setXSpeed(int xSpeed){
        this.xSpeed = xSpeed;
    }

    public int getYSpeed(){
        return ySpeed;
    }

    public void setYSpeed(int ySpeed){
        this.ySpeed = ySpeed;
    }
    public void setSpeed(int speed, double angdeg){
        xSpeed = (int) (speed * Math.cos(Math.toRadians(angdeg)));
        ySpeed = -1 * (int) (speed * Math.sin(Math.toRadians(angdeg)));
//        log.debug(getClass().getSimpleName() + " ("+ x + "," + y +") speed=" + String.format("%.2f", getSpeed()) + " angdeg=" + String.format("%.2f", getAngDeg()) );
    }

    public double getSpeed() {
        return Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed);
    }

    public double getAngDeg() {
        return (Math.toDegrees(Math.atan2(-ySpeed, xSpeed))+360)%360;
    }

    public Rect getRect(){
        return new Rect(x,y,x+width,y+height);
    }

    public Rect[] getRects(){
        Rect[] rects = new Rect[1];
        rects[0] = getRect();
        return rects;
    }

    public boolean collides(ASprite sprite){
        for(Rect rA : this.getRects()){
            for(Rect rB : sprite.getRects()){
                if (Rect.intersects(rA,rB) || rB.contains(rA) || rA.contains(rB)) return true;
            }
        }
        return false;
    }

    public void draw(GraphicsContext gc) {
        int srcX = currentFrame * (width + colSpace);
        int srcY = side * (height + rowSpace);
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x+width, y+height);
//        Rect dst = new Rect(x-width/2, y-height/2, x-width/2+width, y-height/2+height);
        gc.drawImage(img, src.left, src.top, src.width(), src.height(),
                dst.left, dst.top, dst.width(), dst.height());
    }

    public abstract void update();

}
