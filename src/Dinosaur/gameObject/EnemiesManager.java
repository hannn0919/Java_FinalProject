package Dinosaur.gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Dinosaur.util.Resource;

public class EnemiesManager {
    private BufferedImage cactus1;
    private BufferedImage cactus2;
    private BufferedImage bug;
    private Random rand;

    private List<Enemy> enemies;
    private MainCharacter mainCharacter;

    public EnemiesManager(MainCharacter mainCharacter) {
        rand = new Random();
        cactus1 = Resource.getResouceImage("data/dinosaur/水灘1.png");
        cactus2 = Resource.getResouceImage("data/dinosaur/水灘2.png");
        bug = Resource.getResouceImage("data/dinosaur/bug.png");
        enemies = new ArrayList<Enemy>();
        this.mainCharacter = mainCharacter;
        enemies.add(createEnemy());
    }

    public void update() {
        for(Enemy e : enemies) {
            e.update();
        }
        Enemy enemy = enemies.get(0);
        if(enemy.isOutOfScreen()) {
            enemies.clear();
            enemies.add(createEnemy());
        }
    }

    public void draw(Graphics g) {
        for(Enemy e : enemies) {
            e.draw(g);
        }
    }

    private Enemy createEnemy() {
        int type = rand.nextInt(3);
        if(type == 0) {
            return new Water(mainCharacter, 1150, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
        }
        else if(type == 1) {
            return new Water(mainCharacter, 1150, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
        } else {
            return new Bug(mainCharacter, 1150, bug.getWidth() - 10, bug.getHeight() - 10, bug);
        }
    }

    public boolean isCollision() {
        for(Enemy e : enemies) {
            if (mainCharacter.getBound().intersects(e.getBound())) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        enemies.clear();
        enemies.add(createEnemy());
    }
}
