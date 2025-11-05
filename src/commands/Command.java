package commands;

import entity.Player;

public interface Command {
    void execute(Player player);
}
