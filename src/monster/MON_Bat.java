package monster;

import main.GamePanel;

public abstract class MON_Bat extends Monster {

    public MON_Bat(GamePanel gp) {
        super(gp);
        name = "Bat";
        defaultSpeed = 4;
        defense = 0;
        type = type_monster;
    }
}
