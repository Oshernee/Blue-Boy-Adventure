package entity.decorator;

import entity.Entity;
import main.GamePanel;

public abstract class EquipmentDecorator extends Entity {
    
    protected Entity baseEquipment;
    
    public EquipmentDecorator(Entity equipment) {
        super(equipment.getGp());
        this.baseEquipment = equipment;
        
        // Copy ALL base properties
        this.type = equipment.type;
        this.name = equipment.name;
        this.description = equipment.description;
        this.price = equipment.price;
        
        // Copy equipment stats
        this.attackValue = equipment.attackValue;
        this.defenseValue = equipment.defenseValue;
        this.attackArea = equipment.attackArea;
        this.motion1_duration = equipment.motion1_duration;
        this.motion2_duration = equipment.motion2_duration;
        this.knockBackPower = equipment.knockBackPower;
        
        // Copy special effects
        this.lifeStealPercent = equipment.lifeStealPercent;
        this.criticalChance = equipment.criticalChance;
        
        // Copy collision/visual properties
        this.solidArea = equipment.solidArea;
        this.solidAreaDefaultX = equipment.solidAreaDefaultX;
        this.solidAreaDefaultY = equipment.solidAreaDefaultY;
        this.collision = equipment.collision;
        this.down1 = equipment.down1;
    }
    
    protected GamePanel getGamePanel() {
        return gp;
    }
    
    @Override
    public boolean use(Entity user) {
        return baseEquipment.use(user);
    }
}