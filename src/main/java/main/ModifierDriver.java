package main;

import entity.modificador.GoodModifiers.BallVelocityUp;
import entity.modificador.GoodModifiers.PaddleVelocityUp;
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
    private final int MINI_TIME = 1, MAXI_TIME = 2;
    private final int SLEEP_TIME_MODIFIER = 100;

    public ModifierDriver(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.randomSleepTime = random.nextInt(MINI_TIME, MAXI_TIME) * SLEEP_TIME_MODIFIER;
    }


    public void updateModifier() {
        if (activeModifier)
            currentModifier.update();
    }

    public void draw(Graphics2D g2) {

        if (currentModifier == null || currentModifier.entityXWallCollision)
            selectRandomModifier();

        if (activeModifier)
            currentModifier.draw(g2);

        if (newModifierCondition()) {
            currentModifier = null;
            activeModifier = false;
        }
    }

    private boolean newModifierCondition() {
        return currentModifier != null && (currentModifier.entityPaddleCollision || currentModifier.entityXWallCollision);
    }

    private void selectRandomModifier() {
        modifierCounter++;

        if (modifierCounter == randomSleepTime) {
            int randomModifierIndex = random.nextInt(2);
            randomSleepTime = random.nextInt(MINI_TIME, MAXI_TIME) * SLEEP_TIME_MODIFIER;

            currentModifier = randomBeneficialModifierSelection(1);

            activeModifier = true;
            modifierCounter = 0;
        }
    }

    private Modifier randomBeneficialModifierSelection(int randomModifierIndex) {
        Modifier selectedModifier;
        switch (randomModifierIndex) {
            case 0 -> selectedModifier = new BallVelocityUp(gamePanel);
            case 1 -> selectedModifier = new PaddleVelocityUp(gamePanel);
            default -> selectedModifier = null;
        }
        return selectedModifier;
    }
}
