package commands;

import entity.Player;

public class AttackCommand implements Command {
    @Override
    public void execute(Player player) {
        player.attackAction();
    }
}
