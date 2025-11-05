package commands;

import entity.Player;

public class MoveDownCommand implements Command {
    @Override
    public void execute(Player player) {
        player.direction = "down";
    }
}

