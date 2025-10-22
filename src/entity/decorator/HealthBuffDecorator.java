package entity.decorator;

import entity.Entity;

public class HealthBuffDecorator extends EquipmentDecorator {
    
    private int healthBonus;
    
    public HealthBuffDecorator(Entity equipment, int healthBonus) {
        super(equipment);
        this.healthBonus = healthBonus;
        
        // Update name
        this.name = baseEquipment.name + " of Vitality";
        updateDescription();
        
        // Increase price
        this.price = (int)(baseEquipment.price * 1.4);
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
                          "\n+Max HP: +" + healthBonus;
    }
    
    // This method can be called manually to apply the buff
    public void applyBuff(Entity user) {
        user.maxLife += healthBonus;
        user.life += healthBonus;
        
        if(getGamePanel() != null) {
            getGamePanel().playSE(2);
            getGamePanel().ui.addMessage("Max HP +" + healthBonus + "!");
        }
    }
    
    @Override
    public boolean use(Entity user) {
        boolean result = super.use(user);
        
        if(result) {
            applyBuff(user);
        }
        
        return result;
    }
    
    public int getHealthBonus() {
        return healthBonus;
    }
}