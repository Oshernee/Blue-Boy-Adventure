package commands;

import entity.Player;

public class MoveUpCommand implements Command {
    @Override
    public void execute(Player player) {
        player.direction = "up";
    }
}

