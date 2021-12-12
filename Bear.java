package com.mygame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Bear extends JLabel {
    private Boolean can_jump;
    private int speed;
    private Double[] image_anchor;
    private Double[] position;
    private int y;

    public Bear(){
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream("./bear.png"));
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            setIcon(imageIcon);

            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();
            setBounds(100, 445, width, height);
        }catch (Exception e){

        }
    }

    public void jump(Double h){
        if(this.can_jump){
            this.y += 1;
            this.speed -= Math.max(Math.min(h, 10), 7);
            this.can_jump = false;
        }
    }

    public void land(int _y){
        if(this.y > _y - 30){
            this.speed = 0;
            this.can_jump = true;
            this.y = _y;
        }
    }

    public void update(int dt){
        this.speed += 10 * dt;
        this.y -= this.speed;

        if(this.y < -80){
            this.reset();
        }
    }

    public void reset(){
        this.can_jump = false;
        this.speed = 0;
        this.position = new Double[]{100D, 300D};
    }
}