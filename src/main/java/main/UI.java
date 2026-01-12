package main;

import java.awt.*;

public class UI {

    GamePanel gamePanel;
    Font arial_48, arial_60;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_48 = new Font("Arial", Font.PLAIN, gamePanel.tileSize);
        arial_60 = new Font("Arial", Font.PLAIN, 60);
    }

    public void drawPlayerCounters(Graphics2D g2) {
        g2.setFont(arial_48);
        g2.setColor(Color.WHITE);
        drawPlayer1Counter(g2);
        drawPlayer2Counter(g2);
    }

    public void drawPlayer1Counter(Graphics2D g2) {
        g2.drawString(String.valueOf(gamePanel.paddlePlayer1.playerCounter), gamePanel.tileSize, gamePanel.tileSize);
    }

    public void drawPlayer2Counter(Graphics2D g2) {
        String text = String.valueOf(gamePanel.paddlePlayer2.playerCounter);
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int x = gamePanel.screenWidth - gamePanel.tileSize - textWidth;
        int y = gamePanel.tileSize;
        g2.drawString(text, x, y);
    }

    public void drawWinner(Graphics2D g2, String winnerPaddle) {
        g2.setFont(arial_60);
        g2.setColor(Color.WHITE);
        String message = winnerPaddle + " WINS!";
        FontMetrics fm = g2.getFontMetrics();
        int messgeWidth = fm.stringWidth(message);
        int x = gamePanel.screenWidth / 2 - messgeWidth /2 ;
        int y = gamePanel.screenHeight / 2 - gamePanel.tileSize ;

        g2.drawString(message, x, y);
        gamePanel.gameThread.interrupt();
    }
}
