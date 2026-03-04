package entity.modifiers.GoodModifiers;

import entity.modifiers.Modifier;
import main.GamePanel;

import java.awt.*;

public class PaddleDirectionChange extends Modifier {


    public PaddleDirectionChange(GamePanel gamePanel) {
        super(gamePanel, Color.MAGENTA);
    }
    
    @Override
    protected void modifierReaction() {
        this.gamePanel.gameBall.speedX = -this.gamePanel.gameBall.speedX;
    }
}
