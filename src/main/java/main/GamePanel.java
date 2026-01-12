package main;

import ball.Ball;
import keyHandler.KeyHandler;
import paddles.Paddle;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS CONSTANTS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 pixels
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol; // Game window max width
    public final int screenHeight = tileSize * maxScreenRow; // Game window max height
    public final int oneSecondInMilliseconds = 1000000000; // One second converted to nanoseconds

    // Paddle
    KeyHandler keyHandler = new KeyHandler();
    Paddle paddlePlayer1 = new Paddle(this, keyHandler, tileSize / 2, true);
    Paddle paddlePlayer2 = new Paddle(this, keyHandler, (screenWidth - tileSize) + ((tileSize / 2) - (tileSize / 3)), false);
    Paddle[] paddles = {paddlePlayer1, paddlePlayer2};
    Ball gameBall = new Ball(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this, paddles);
    GameRestarter gameRestarter = new GameRestarter(this);
    UI ui = new UI(this);
    public Sound soundEffect = new Sound();


    //FPS objective
    int FPS = 60;

    public Thread gameThread; // Represents our game

    public GamePanel() {
        // We use the dimensions we created before and put the background color to black
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // This helps to improve game's rendering performance
        this.setDoubleBuffered(true);

        // We add the KeyHandler to our game
        this.addKeyListener(keyHandler);
        // This makes that the game can receive inputs from the user
        this.setFocusable(true);
    }


    public void update() {
        paddlePlayer1.update();
        paddlePlayer2.update();
        gameBall.update();
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) oneSecondInMilliseconds / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread.isAlive()) {
            if (gameThread.isInterrupted()) {
                break;
            }

            currentTime = System.nanoTime();
            delta += (double) (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {

                update();
                restartPosition();
                repaint();
                delta--;
            }
        }
    }

    private void restartPosition() {
        if (this.gameBall.worldX < 0 || this.gameBall.worldX > screenWidth)
            this.gameRestarter.restart();

    }

    private void endGame(Graphics2D g2) {
        if (this.paddlePlayer1.playerCounter == 5) {
            ui.drawWinner(g2, "PLAYER 1");
        } else if (this.paddlePlayer2.playerCounter == 5) {
            ui.drawWinner(g2, "PLAYER 2");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        ui.drawPlayerCounters(g2);
        this.collisionChecker.draw(g2, gameBall);
        paintLine(g2);

        endGame(g2);

        gameBall.draw(g2);


        paddlePlayer1.draw(g2);
        paddlePlayer2.draw(g2);

        g2.dispose();
    }

    private void paintLine(Graphics2D g2) {
        // Indicates the thickness of the line
        g2.setStroke(new BasicStroke(5));
        // Sets the color of the line
        g2.setColor(Color.white);
        // Draws the line in the exact middle of the screen
        g2.drawLine(screenWidth / 2, 0, screenWidth / 2, screenHeight);
        // Then we put the thickness back to normal
        g2.setStroke(new BasicStroke(1));
    }


}
