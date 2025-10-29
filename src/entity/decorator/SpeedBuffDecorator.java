package entity.decorator;

import entity.Entity;

public class SpeedBuffDecorator extends EquipmentDecorator {
    
    private int speedBonus;
    private boolean buffApplied = false;
    
    public SpeedBuffDecorator(Entity equipment, int speedBonus) {
        super(equipment);
        this.speedBonus = speedBonus;
        
        this.name = baseEquipment.name + " of Swiftness";
        updateDescription();

        this.price = (int)(baseEquipment.price * 1.6);
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
                          "\n+Speed: +" + speedBonus;
    }
    
    public void applyBuff(Entity user) {
        if(!buffApplied) {
            user.speed += speedBonus;
            buffApplied = true;
            
            if(getGamePanel() != null) {
                getGamePanel().playSE(2);
                getGamePanel().ui.addMessage("Speed +" + speedBonus + "!");
            }
        }
    }
    
    public void removeBuff(Entity user) {
        if(buffApplied) {
            user.speed -= speedBonus;
            buffApplied = false;
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
    
    public int getSpeedBonus() {
        return speedBonus;
    }
}