package factory;

import main.GamePanel;
import monster.Monster;

public interface MonsterFactory {
    Monster createSlime(GamePanel gp);
    Monster createBat(GamePanel gp);
}
