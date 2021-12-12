package com.mygame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.swing.*;

public class VoiceBar extends JLabel{

    public VoiceBar(){
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream("./grass.png"));
            Graphics2D g = bufferedImage.createGraphics();
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

            bufferedImage.setRGB(0,0, 0x0000ff);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            setIcon(imageIcon);
            int width = (int)(imageIcon.getIconWidth() * 0.1);
            int height = (int)(imageIcon.getIconHeight() * 0.1);
            setBounds(20, 600 - 450, width, height);
        }catch (Exception e){

        }
    }

    public void update(int width){
        setBounds(20, 600 - 450, width, this.getHeight());
    }
}