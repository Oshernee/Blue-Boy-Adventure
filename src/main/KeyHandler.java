package main;

public class KeyHandler {

    private final Controls keyboard;
    private final Controls controller;
    private final GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean enterPressed, shotKeyPressed, spacePressed;
    private boolean prevPause, prevCharacter, prevMap, prevEscape;
    private boolean prevLeft, prevRight;
    private boolean prevUp, prevDown, prevEnter;
    public boolean showDebugText = false;
    public boolean godModeOn = false;

    public KeyHandler(GamePanel gp, Controls keyboard, Controls controller) {
        this.gp = gp;
        this.keyboard = keyboard;
        this.controller = controller;
    }

    private boolean justPressed(boolean current, boolean previous) {
        return current && !previous;
    }

    public void update() {
        keyboard.update();
        controller.update();

        upPressed = keyboard.isUpPressed() || controller.isUpPressed();
        downPressed = keyboard.isDownPressed() || controller.isDownPressed();
        leftPressed = keyboard.isLeftPressed() || controller.isLeftPressed();
        rightPressed = keyboard.isRightPressed() || controller.isRightPressed();

        enterPressed = keyboard.isEnterPressed() || controller.isEnterPressed();
        shotKeyPressed = keyboard.isShotPressed() || controller.isShotPressed();
        spacePressed = keyboard.isSpacePressed() || controller.isSpacePressed();

        boolean pausePressed = keyboard.isPausePressed() || controller.isPausePressed();
        boolean characterPressed = keyboard.isCharacterPressed() || controller.isCharacterPressed();
        boolean mapPressed = keyboard.isMapPressed() || controller.isMapPressed();
        boolean escapePressed = keyboard.isEscapePressed() || controller.isEscapePressed();

        if (gp.gameState == gp.titleState) {
            handleTitleInput();
        } else if (gp.gameState == gp.playState) {
            handlePlayInput(pausePressed, characterPressed, mapPressed, escapePressed);
        } else if (gp.gameState == gp.pauseState) {
            handlePauseInput();
        } else if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
            handleDialogueInput();
        } else if (gp.gameState == gp.characterState) {
            handleCharacterInput();
        } else if (gp.gameState == gp.optionsState) {
            handleOptionsInput();
        } else if (gp.gameState == gp.gameOverState) {
            handleGameOverInput();
        } else if (gp.gameState == gp.tradeState) {
            handleTradeInput();
        } else if (gp.gameState == gp.mapState) {
            handleMapInput();
        }

        prevUp = upPressed;
        prevDown = downPressed;
        prevLeft = leftPressed;
        prevRight = rightPressed;
        prevEnter = enterPressed;
        prevPause = pausePressed;
        prevCharacter = characterPressed;
        prevMap = mapPressed;
        prevEscape = escapePressed;
    }

    private void handleTitleInput() {
        boolean up = justPressed(upPressed, prevUp);
        boolean down = justPressed(downPressed, prevDown);
        boolean enter = justPressed(enterPressed, prevEnter);

        if (up) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) gp.ui.commandNum = 2;
        }
        if (down) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) gp.ui.commandNum = 0;
        }

        if (enter) {
            if (gp.ui.titleScreenState == 0) {
                switch (gp.ui.commandNum) {
                    case 0 -> gp.ui.titleScreenState = 1;
                    case 1 -> {
                        gp.saveLoad.load();
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    case 2 -> System.exit(0);
                }
            } else if (gp.ui.titleScreenState == 1) {
                switch (gp.ui.commandNum) {
                    case 0 -> System.out.println("Fighter chosen!");
                    case 1 -> System.out.println("Thief chosen!");
                    case 2 -> System.out.println("Sorcerer chosen!");
                    case 3 -> {
                        gp.ui.titleScreenState = 0;
                        return;
                    }
                }
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
        }
    }


    private void handlePlayInput(boolean pausePressed, boolean characterPressed, boolean mapPressed, boolean escapePressed) {
        if (justPressed(pausePressed, prevPause)) {
            gp.gameState = gp.pauseState;
        }
        if (justPressed(characterPressed, prevCharacter)) {
            gp.gameState = gp.characterState;
        }
        if (justPressed(mapPressed, prevMap)) {
            gp.gameState = gp.mapState;
        }
        if (justPressed(escapePressed, prevEscape)) {
            gp.gameState = gp.optionsState;
        }

        if (shotKeyPressed) shotKeyPressed = true;
        if (spacePressed) spacePressed = true;
    }

    private void handlePauseInput() {
        boolean pause = justPressed(
                keyboard.isPausePressed() || controller.isPausePressed(),
                prevPause
        );
        if (pause) {
            gp.gameState = gp.playState;
        }
    }

    private void handleDialogueInput() {
        if (enterPressed) enterPressed = true;
    }

    private void handleCharacterInput() {
        boolean up = justPressed(upPressed, prevUp);
        boolean down = justPressed(downPressed, prevDown);
        boolean left = justPressed(leftPressed, prevLeft);
        boolean right = justPressed(rightPressed, prevRight);
        boolean enter = justPressed(enterPressed, prevEnter);
        boolean character = justPressed(
                keyboard.isCharacterPressed() || controller.isCharacterPressed(),
                prevCharacter
        );

        if (up) {
            if (gp.ui.playerSlotRow > 0) gp.ui.playerSlotRow--;
            gp.playSE(9);
        }
        if (down) {
            if (gp.ui.playerSlotRow < 3) gp.ui.playerSlotRow++;
            gp.playSE(9);
        }
        if (left) {
            if (gp.ui.playerSlotCol > 0) gp.ui.playerSlotCol--;
            gp.playSE(9);
        }
        if (right) {
            if (gp.ui.playerSlotCol < 4) gp.ui.playerSlotCol++;
            gp.playSE(9);
        }

        if (enter) {
            gp.player.selectItem();
            gp.playSE(9);
        }

        if (character) {
            gp.gameState = gp.playState;
        }
    }

    private void handleOptionsInput() {
        if (enterPressed) enterPressed = true;
    }

    private void handleGameOverInput() {
        if (upPressed) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) gp.ui.commandNum = 1;
        }
        if (downPressed) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) gp.ui.commandNum = 0;
        }
        if (enterPressed) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.resetGame(false);
                gp.playMusic(0);
            } else if (gp.ui.commandNum == 1) {
                gp.ui.titleScreenState = 0;
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
    }

    private void handleTradeInput() {
        if (enterPressed) enterPressed = true;
    }

    private void handleMapInput() {
        if (enterPressed) gp.gameState = gp.playState;
    }
}
