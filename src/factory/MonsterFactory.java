package factory;

import main.GamePanel;
import monster.*;

public class MonsterFactory {

    public static Monster createMonster(GamePanel gp, String type) {
        switch (type.toLowerCase()) {
            case "bat":
                return new MON_Bat(gp);
            case "orc":
                return new MON_Orc(gp);
            case "skeletonlord":
                return new MON_SkeletonLord(gp);
            case "slime":
            case "redslime":
            case "greenslime":
                return SlimeFactory.createSlime(gp, type);
            default:
                throw new IllegalArgumentException("Unknown monster type: " + type);
        }
    }
}
