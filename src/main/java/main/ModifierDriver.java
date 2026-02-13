package main;

import entity.modificador.BallVelocityUp;
import entity.modificador.Modifier;

import java.awt.*;
import java.util.Random;

public class ModifierDriver {

    // UNIQUE CLASS VARIABLES
    private final GamePanel gamePanel;
    private int modifierCounter = 0;
    private Modifier currentModifier;
    private boolean activeModifier = false;
    private int randomSleepTime;
    private final Random random = new Random();

    // CONSTANTS
    private final int MINI_TIME = 3, MAXI_TIME = 7;
    private final int SLEEP_TIME_MODIFIER = 100;

    public ModifierDriver(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.randomSleepTime = random.nextInt(MINI_TIME, MAXI_TIME);
    }


    public void updateModifier() {
        if (activeModifier)
            currentModifier.update();
    }

    public void draw(Graphics2D g2) {

        if (currentModifier == null)
            selectRandomModifier();

        if (activeModifier)
            currentModifier.draw(g2);

        if (currentModifier != null && currentModifier.ballPaddleCollision) {
            currentModifier = null;
            activeModifier = false;
        }

    }

    private void selectRandomModifier() {
        modifierCounter++;

        if (modifierCounter == (randomSleepTime * SLEEP_TIME_MODIFIER)) {
//            int randomModifierIndex = random.nextInt(goodModifiers.length);
            randomSleepTime = random.nextInt(MINI_TIME, MAXI_TIME);

            currentModifier = randomBeneficialModifierSelection(0);

            activeModifier = true;
            modifierCounter = 0;
        }
    }

    private Modifier randomBeneficialModifierSelection(int randomModifierIndex) {
        Modifier selectedModifier;
        switch (randomModifierIndex) {
            case 0 -> selectedModifier = new BallVelocityUp(gamePanel);
            default -> selectedModifier = null;
        }
        return selectedModifier;
    }
}
