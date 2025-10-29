package entity.decorator;

import entity.Entity;

public class LifeStealDecorator extends EquipmentDecorator {
    
    private int lifeStealPercent;
    
    public LifeStealDecorator(Entity equipment, int lifeStealPercent) {
        super(equipment);
        this.lifeStealPercent = lifeStealPercent;

        this.name = "Vampiric " + baseEquipment.name;
        updateDescription();

        this.price = (int)(baseEquipment.price * 2.0);
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
                          "\n+Life Steal: " + lifeStealPercent + "%";
    }
    
    @Override
    public boolean use(Entity user) {
        boolean result = super.use(user);
        
        if(result && getGamePanel() != null) {
            getGamePanel().playSE(10); // blood/dark sound
        }
        
        return result;
    }
    
    public int getLifeStealPercent() {
        return lifeStealPercent;
    }
}