package entity;

import main.GamePanel;

import java.awt.*;

public class NPC_Enchanter extends Entity {
    
    public NPC_Enchanter(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;

        getImage();
        setDialogue();

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;
    }
    
    public void getImage() {
        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }
    
    public void setDialogue() {
        dialogues[0][0] = "Greetings, adventurer.\nI can enhance your equipment\nwith magical enchantments.";
        dialogues[1][0] = "May your blade strike true!";
        dialogues[2][0] = "You need to select an item first!";
        dialogues[3][0] = "That item cannot be enchanted!";
    }
    
    public void speak() {
        facePlayer();
        gp.gameState = gp.enchantState;
        gp.ui.npc = this;
    }
    
    @Override
    public void setAction() {
        // Enchanter stays still
    }
}
