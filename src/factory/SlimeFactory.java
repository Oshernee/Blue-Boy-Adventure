package factory;

import main.GamePanel;
import monster.*;

public class SlimeFactory {

    public static MON_Slime createSlime(GamePanel gp, String type) {
        switch (type.toLowerCase()) {
            case "redslime":
                return new MON_RedSlime(gp);
            case "greenslime":
                return new MON_GreenSlime(gp);
            default:
                throw new IllegalArgumentException("Unknown slime type: " + type);
        }
    }
}
