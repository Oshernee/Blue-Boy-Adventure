package entity.decorator;

import entity.Entity;

public class CriticalChanceDecorator extends EquipmentDecorator {
    
    private int critChance; 
    
    public CriticalChanceDecorator(Entity equipment, int critChance) {
        super(equipment);
        this.critChance = Math.min(critChance, 100); 

        updateDescription();

        this.price = (int)(baseEquipment.price * 1.7);
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
                          "\n+Critical Chance: " + critChance + "%";
    }
    
    @Override
    public boolean use(Entity user) {
        boolean result = super.use(user);
        
        if(result && getGamePanel() != null) {
            getGamePanel().playSE(3);
        }
        
        return result;
    }
    
    public int getCritChance() {
        return critChance;
    }
}