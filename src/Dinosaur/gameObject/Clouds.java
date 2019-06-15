package Dinosaur.gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Dinosaur.util.Resource;

public class Clouds {
    private List<ImageCloud> listCloud;
    private BufferedImage cloud;

    private MainCharacter mainCharacter;

    public Clouds(int width, MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        cloud = Resource.getResouceImage("data/dinosaur/cloud1.png");
        listCloud = new ArrayList<ImageCloud>();
        ImageCloud imageCloud = new ImageCloud();
        imageCloud.posX = 0;
        imageCloud.posY = 80;
        listCloud.add(imageCloud);


        imageCloud = new ImageCloud();
        imageCloud.posX = 300;
        imageCloud.posY = 130;
        listCloud.add(imageCloud);

        imageCloud = new ImageCloud();
        imageCloud.posX = 450;
        imageCloud.posY = 100;
        listCloud.add(imageCloud);


        imageCloud = new ImageCloud();
        imageCloud.posX = 750;
        imageCloud.posY = 150;
        listCloud.add(imageCloud);


        imageCloud = new ImageCloud();
        imageCloud.posX = 1050;
        imageCloud.posY = 110;
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
            firstElement.posX = 1200;
            listCloud.add(firstElement);
        }
    }

    public void draw(Graphics g) {
        for(int i = 0; i< listCloud.size();++i){
            g.drawImage(cloud, (int) listCloud.get(i).posX, listCloud.get(i).posY, null);
        }
        /*
        for(ImageCloud imgLand : listCloud) {
            g.drawImage(cloud, (int) imgLand.posX, imgLand.posY, null);
        }*/
    }

    private class ImageCloud {
        float posX;
        int posY;
    }
}
