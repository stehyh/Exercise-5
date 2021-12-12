package com.mygame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        PortAudio.initialize();
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gamePanel.start();
    }
}
