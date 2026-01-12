package main;

import ball.Ball;

public class GameRestarter {
    GamePanel gamePanel;

    public GameRestarter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void restart() {
        addPoints();

        gamePanel.paddlePlayer1.restartPaddleOriginalPositions();
        gamePanel.paddlePlayer2.restartPaddleOriginalPositions();

        gamePanel.gameBall.restarBallOriginalPosition();
    }

    public void addPoints() {
        if (gamePanel.gameBall.worldX < 0)
            gamePanel.paddlePlayer2.playerCounter += 1;

        if (gamePanel.gameBall.worldX > gamePanel.screenWidth)
            gamePanel.paddlePlayer1.playerCounter += 1;
    }



}
