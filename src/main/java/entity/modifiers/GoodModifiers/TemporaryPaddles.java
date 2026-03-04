package entity.modifiers.GoodModifiers;

import entity.modifiers.Modifier;
import main.GamePanel;

import java.awt.*;

public class TemporaryPaddles extends Modifier {

    private boolean topPaddleCollision = false;
    private boolean bottomPaddleCollision = false;

    public TemporaryPaddles(GamePanel gamePanel) {
        super(gamePanel, Color.ORANGE);
    }

    @Override
    protected void modifierReaction() {
        this.gamePanel.collisionChecker.collidedPallet.modifierCollision = true;
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    private void paintTopLine(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(worldX, worldY, gamePanel.tileSize / 4, gamePanel.tileSize * 3);

        // We set the color of  rectangle that indicates the collision of our character
        g2.setColor(Color.red);
        // Then we drawPlayerCounters that rectangle to see it in the screen
        g2.drawRect(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }
}
