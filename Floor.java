package com.mygame;

import javax.swing.*;

public class Floor extends JLabel {
    public Floor(){
        Integer[] pos = new Integer[]{0, 0};
        for(int i = 0; i < 100; i++){
            Grass grass = new Grass(pos);
            add(grass);
            pos[0] = grass.getX() + grass.getWidth();
            pos[1] = grass.getHeight();
        }
        setBounds(0, 0, pos[0], 600);
    }

    public void update(){
        setBounds(this.getX() - 10, 0, this.getWidth(), 600);
    }
}
