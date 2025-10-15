package factory;

import main.GamePanel;
import monster.*;

public class GreenMonsterFactory implements MonsterFactory {

    @Override
    public Monster createSlime(GamePanel gp) {
        return new MON_GreenSlime(gp);
    }

    @Override
    public Monster createBat(GamePanel gp) {
        return new MON_GreenBat(gp);
    }

}
