package entity.modificador;

import main.GamePanel;

import java.awt.*;

public class BallVelocityUp extends Modifier {

    public BallVelocityUp(GamePanel gamePanel) {
        super(gamePanel, Color.GREEN);
    }

    @Override
    public void modifierReaction() {
        this.gamePanel.gameBall.speedUp();
    }
}
