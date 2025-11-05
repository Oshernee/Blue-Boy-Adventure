package commands;

import entity.Player;

public class MoveRightCommand implements Command {
    @Override
    public void execute(Player player) {
        player.direction = "right";
    }
}

