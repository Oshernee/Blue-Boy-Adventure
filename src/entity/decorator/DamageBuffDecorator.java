package entity.decorator;

import entity.Entity;

public class DamageBuffDecorator extends EquipmentDecorator {
    
    private int bonusAttack;
    
    public DamageBuffDecorator(Entity equipment, int bonusAttack) {
        super(equipment);
        this.bonusAttack = bonusAttack;
        
        // Enhance attack value
        this.attackValue = baseEquipment.attackValue + bonusAttack;
        
        // IMPORTANT: Copy defenseValue (don't modify it)
        this.defenseValue = baseEquipment.defenseValue;
        
        // Update name and description
        this.name = baseEquipment.name + " +DMG";
        updateDescription();
        
        // Increase price
        this.price = (int)(baseEquipment.price * 1.5);
    }
    
    private void updateDescription() {
        String baseDesc = baseEquipment.description;
        if(baseDesc.startsWith("[")) {
            int endBracket = baseDesc.indexOf("]");
            if(endBracket != -1) {
                baseDesc = baseDesc.substring(endBracket + 1);
            }
        }
        
        this.description = "[" + this.name + "]" + baseDesc + 
                          "\n+Damage Buff: +" + bonusAttack + " ATK";
    }
    
    @Override
    public boolean use(Entity user) {
        boolean result = super.use(user);
        
        if(result && getGamePanel() != null) {
            getGamePanel().playSE(3);
        }
        
        return result;
    }
    
    public int getBonusAttack() {
        return bonusAttack;
    }
}