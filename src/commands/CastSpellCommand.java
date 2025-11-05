package commands;

import entity.Player;

public class CastSpellCommand implements Command {
    @Override
    public void execute(Player player) {
        player.castSpellAction();
    }
}
