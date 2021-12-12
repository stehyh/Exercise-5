package com.mygame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.*;

import static java.awt.Image.SCALE_SMOOTH;

public class Grass extends JLabel {
    private Double[] imageAnchor;
    private Double[] position;
    private Double scaleX;
    private Double scaleY;
    BufferedImage grass;

    public Grass(Integer[] pos){
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream("./grass.png"));
            Graphics2D g = bufferedImage.createGraphics();

            int x = pos[0];
            int y = pos[1];
            imageAnchor = new Double[]{0D, 0D};
            if(x == 0){
                scaleX = 5D;
                scaleY = 1D;
            }else{
                scaleX = 0.5 + Math.random() * 1.5;
                scaleY = Math.min(Math.max(y - 50 + Math.random() * 100, 50), 300) / 100.0D;
                x = (int)(x + 50 + Math.random() * 100);
                y = 0;
            }
            g.scale(scaleX, scaleY);
            int width = (int)(bufferedImage.getWidth() * scaleX);
            int height = (int)(bufferedImage.getHeight() * scaleY);
            Image image = bufferedImage.getScaledInstance(width, height, SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(image);
            setIcon(imageIcon);
            setBounds(x, 600 - height, width, height);
        }catch (Exception e){

        }
    }
}
