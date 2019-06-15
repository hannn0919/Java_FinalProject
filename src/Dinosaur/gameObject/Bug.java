package Dinosaur.gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bug extends Enemy {

    public static final int Y_LAND = 400;

    private int posX;
    private int width;
    private int height;

    private BufferedImage image;
    private MainCharacter mainCharacter;

    private Rectangle rectBound;

    public Bug(MainCharacter mainCharacter, int posX, int width, int height, BufferedImage image) {
        this.posX = posX;
        this.width = width;
        this.height = height;
        this.image = image;
        this.mainCharacter = mainCharacter;
        rectBound = new Rectangle();
    }

    public void update() {
        posX -= mainCharacter.getSpeedX();
    }

    public void draw(Graphics g) {
        g.drawImage(image, posX, Y_LAND - image.getHeight(), null);
        g.setColor(Color.red);
    }

    public Rectangle getBound() {
        rectBound = new Rectangle();
        rectBound.x = posX + (image.getWidth() - width)/2;
        rectBound.y = Y_LAND - image.getHeight() + (image.getHeight() - height)/2;
        rectBound.width = width;
        rectBound.height = height;
        return rectBound;
    }

    @Override
    public boolean isOutOfScreen() {
        if(posX < -image.getWidth()) {
            return true;
        }
        return false;
    }

}
