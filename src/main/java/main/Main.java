package main;

import menu.PongMenu;

import javax.swing.*;

public class Main {
    static JFrame window;
    static PongMenu pongMenu;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pong Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }

    public static void cerrarPartida() {
        javax.swing.Timer timer = new javax.swing.Timer(1500, e -> {
            window.dispose();
            pongMenu = new PongMenu();
        });
        timer.setRepeats(false);
        timer.start();

    }
}
