package Dinosaur.gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Dinosaur.userinterface.GameWindow;
import Dinosaur.util.Resource;

public class Clouds {
    private List<ImageCloud> listCloud;
    private BufferedImage cloud;

    private MainCharacter mainCharacter;

    public Clouds(int width, MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        cloud = Resource.getResouceImage("data/dinosaur/cloud.png");
        listCloud = new ArrayList<ImageCloud>();
        // maybe can use for loop and random;
        ImageCloud imageCloud = new ImageCloud();
        imageCloud.posX = 0;
        imageCloud.posY = 210;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 150;
        imageCloud.posY = 220;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 300;
        imageCloud.posY = 230;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 450;
        imageCloud.posY = 200;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 600;
        imageCloud.posY = 240;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 750;
        imageCloud.posY = 180;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 900;
        imageCloud.posY = 250;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 1050;
        imageCloud.posY = 210;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 1200;
        imageCloud.posY = 190;
        listCloud.add(imageCloud);
    }

    public void update(){
        Iterator<ImageCloud> itr = listCloud.iterator();
        ImageCloud firstElement = itr.next();
        firstElement.posX -= mainCharacter.getSpeedX()/8;
        while(itr.hasNext()) {
            ImageCloud element = itr.next();
            element.posX -= mainCharacter.getSpeedX()/8;
        }
        if(firstElement.posX < -cloud.getWidth()) {
            listCloud.remove(firstElement);
            firstElement.posX = GameWindow.SCREEN_WIDTH;
            listCloud.add(firstElement);
        }
    }

    public void draw(Graphics g) {
        for(ImageCloud imgLand : listCloud) {
            g.drawImage(cloud, (int) imgLand.posX, imgLand.posY, null);
        }
    }

    private class ImageCloud {
        float posX;
        int posY;
    }
}
