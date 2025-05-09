package edu.masanz.da.pk2.sprite;
import javafx.scene.image.Image;

public abstract class AItem extends ASprite{
    protected int n = 0;

    public AItem(Image img, int rows, int cols) {
        super(img, rows, cols);
    }

    public AItem(Image img, int rows, int cols, int rowSpace, int colSpace) {
        super(img, rows, cols, rowSpace, colSpace);
    }


}
