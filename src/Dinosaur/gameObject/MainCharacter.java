package Dinosaur.gameObject;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import Dinosaur.util.Animation;
import Dinosaur.util.Resource;
import House.house.House;

public class MainCharacter {
    public static final int LAND_POSY = 380;
    public static final float GRAVITY = 0.4f;

    private static final int NORMAL_RUN = 0;
    private static final int JUMPING = 1;
    private static final int DOWN_RUN = 2;
    private static final int DEATH = 3;

    public  boolean invincible = false;


    private float posY;
    private float posX;
    private float speedX;
    private float speedY;
    private Rectangle rectBound;

    public double score = 0;

    private int state = NORMAL_RUN;

    private Animation normalRunAnim;
    private BufferedImage jumping;
    private Animation downRunAnim;
    private BufferedImage deathImage;

    private AudioClip jumpSound;
    private AudioClip deadSound;
    private AudioClip scoreUpSound;

    private  String character, character_close;

    public MainCharacter(House house) {
        posX = 150;
        posY = LAND_POSY;
        rectBound = new Rectangle();
        normalRunAnim = new Animation(90);
        character = "data/dinosaur/character/LV"+house.getLevel()+"肝.png";
        character_close = "data/dinosaur/character/LV"+house.getLevel()+"肝憋腳.png";
        
        normalRunAnim.addFrame(Resource.getResouceImage(character));
        normalRunAnim.addFrame(Resource.getResouceImage(character_close));
        jumping = Resource.getResouceImage(character);
        downRunAnim = new Animation(90);
        downRunAnim.addFrame(Resource.getResouceImage(character));
        downRunAnim.addFrame(Resource.getResouceImage(character));
        deathImage = Resource.getResouceImage(character);

        try {
            jumpSound =  Applet.newAudioClip(new URL("file","","data/dinosaur/jump.wav"));
            deadSound =  Applet.newAudioClip(new URL("file","","data/dinosaur/dead.wav"));
            scoreUpSound =  Applet.newAudioClip(new URL("file","","data/dinosaur/scoreup.wav"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public float getSpeedX() {
        return speedX;
    }

    public  void setinvIncible(){
        invincible = true;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void draw(Graphics g) {
        switch(state) {
            case NORMAL_RUN:
                g.drawImage(normalRunAnim.getFrame(), (int) posX, (int) posY, null);
                break;
            case JUMPING:
                g.drawImage(jumping, (int) posX, (int) posY, null);
                break;
            case DOWN_RUN:
                g.drawImage(downRunAnim.getFrame(), (int) posX, (int) (posY + 20), null);
                break;
            case DEATH:
                g.drawImage(deathImage, (int) posX, (int) posY, null);
                break;
        }
    }

    public void update() {
        normalRunAnim.updateFrame();
        downRunAnim.updateFrame();
        if(posY >= LAND_POSY) {
            posY = LAND_POSY;
            if(state != DOWN_RUN) {
                state = NORMAL_RUN;
            }
        } else {
            speedY += GRAVITY;
            posY += speedY;
        }
    }

    public void jump() {
        if(posY >= LAND_POSY) {
            if(jumpSound != null) {
                jumpSound.play();
            }
            speedY = -10.5f;
            posY += speedY;
            state = JUMPING;
        }
    }

    public void down(boolean isDown) {
        if(state == JUMPING) {
            return;
        }
        if(isDown) {
            state = DOWN_RUN;
        } else {
            state = NORMAL_RUN;
        }
    }

    public Rectangle getBound() {
        rectBound = new Rectangle();
        if(state == DOWN_RUN) {
            rectBound.x = (int) posX + 5;
            rectBound.y = (int) posY + 20;
            rectBound.width = downRunAnim.getFrame().getWidth() - 10;
            rectBound.height = downRunAnim.getFrame().getHeight();
        } else {
            rectBound.x = (int) posX + 5;
            rectBound.y = (int) posY;
            rectBound.width = normalRunAnim.getFrame().getWidth() - 10;
            rectBound.height = normalRunAnim.getFrame().getHeight();
        }
        return rectBound;
    }

    public void dead(boolean isDeath) {
        if(isDeath) {
            if(score<0)
                score = 0;
        } else {
            state = NORMAL_RUN;
        }
    }

    public void reset() {
        score = 0;
        posY = LAND_POSY;
    }

    public void playDeadSound() {
        deadSound.play();
    }

    public void upScore() {
        score += 0.01;
        if(score % 100 == 0) {
            scoreUpSound.play();
        }
    }
}
