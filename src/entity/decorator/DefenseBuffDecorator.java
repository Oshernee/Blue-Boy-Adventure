package entity.decorator;

import entity.Entity;

public class DefenseBuffDecorator extends EquipmentDecorator {
    
    private int bonusDefense;
    
    public DefenseBuffDecorator(Entity equipment, int bonusDefense) {
        super(equipment);
        this.bonusDefense = bonusDefense;

        this.defenseValue = baseEquipment.defenseValue + bonusDefense;
        this.attackValue = baseEquipment.attackValue;

        this.name = baseEquipment.name + " +DEF";
        updateDescription();

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
                          "\n+Defense Buff: +" + bonusDefense + " DEF";
    }
    
    @Override
    public boolean use(Entity user) {
        boolean result = super.use(user);
        
        if(result && getGamePanel() != null) {
            getGamePanel().playSE(3);
        }
        
        return result;
    }
    
    public int getBonusDefense() {
        return bonusDefense;
    }
}