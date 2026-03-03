package entity.modificador.GoodModifiers;

import entity.modificador.Modifier;
import main.GamePanel;

import java.awt.*;

public class PaddleVelocityUp extends Modifier {

    public PaddleVelocityUp(GamePanel gamePanel) {
        super(gamePanel, Color.CYAN);
    }

    @Override
    protected void modifierReaction() {
        this.gamePanel.collisionChecker.collidedPallet.paddleVelocityUp();
        this.gamePanel.collisionChecker.collidedPallet.modifierCollision = true;
    }
}
