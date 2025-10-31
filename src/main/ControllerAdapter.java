package main;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ControllerAdapter implements Controls {

    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean pausePressed, characterPressed, mapPressed, escapePressed;
    private boolean enterPressed, shotPressed, spacePressed;

    private Controller controller;

    public ControllerAdapter() {
        Controller[] controllers = ControllerEnvironment
                .getDefaultEnvironment()
                .getControllers();

        for (Controller c : controllers) {
            if (c.getType() == Controller.Type.GAMEPAD || c.getType() == Controller.Type.STICK) {
                controller = c;
                System.out.println("Controller connected: " + c.getName());
                break;
            }
        }

        if (controller == null) {
            System.out.println("No controller detected!");
        }
    }

    @Override
    public void update() {
        if (controller == null) return;

        controller.poll();
        Component[] components = controller.getComponents();

        upPressed = downPressed = leftPressed = rightPressed = false;

        for (Component c : components) {
            float value = c.getPollData();
            String id = c.getIdentifier().getName();

            switch (id) {
                case "x" -> {
                    leftPressed = value < -0.5f;
                    rightPressed = value > 0.5f;
                }
                case "y" -> {
                    upPressed = value < -0.5f;
                    downPressed = value > 0.5f;
                }
                case "pov" -> {
                    upPressed = value == 0.25f;
                    rightPressed = value == 0.5f;
                    downPressed = value == 0.75f;
                    leftPressed = value == 1.0f;
                }
            }

            switch (id) {
                case "0" -> enterPressed = value == 1.0f;       // A
                case "1" -> shotPressed = value == 1.0f;        // B
                case "2" -> spacePressed = value == 1.0f;       // X
                case "3" -> pausePressed = value == 1.0f;       // Y
                case "4" -> characterPressed = value == 1.0f;   // LB
                case "5" -> mapPressed = value == 1.0f;         // RB
                case "7" -> escapePressed = value == 1.0f;      // Start/Menu
            }
        }
    }

    @Override
    public boolean isUpPressed() { return upPressed; }
    @Override
    public boolean isDownPressed() { return downPressed; }
    @Override
    public boolean isLeftPressed() { return leftPressed; }
    @Override
    public boolean isRightPressed() { return rightPressed; }
    @Override
    public boolean isEnterPressed() { return enterPressed; }
    @Override
    public boolean isShotPressed() { return shotPressed; }
    @Override
    public boolean isSpacePressed() { return spacePressed; }
    @Override
    public boolean isPausePressed() { return pausePressed; }
    @Override
    public boolean isCharacterPressed() { return characterPressed; }
    @Override
    public boolean isMapPressed() { return mapPressed; }
    @Override
    public boolean isEscapePressed() { return escapePressed; }

//    @Override
//    public void resetKeys() {
//        upPressed = downPressed = leftPressed = rightPressed = false;
//        enterPressed = shotPressed = spacePressed = false;
//        pausePressed = characterPressed = mapPressed = escapePressed = false;
//    }
}
