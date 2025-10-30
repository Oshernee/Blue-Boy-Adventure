package entity.decorator;

import entity.Entity;

public class ManaBuffDecorator extends EquipmentDecorator {
    
    private int manaBonus;
    
    public ManaBuffDecorator(Entity equipment, int manaBonus) {
        super(equipment);
        this.manaBonus = manaBonus;

        updateDescription();

        this.price = (int)(baseEquipment.price * 1.3);
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
                          "\n+Max Mana: +" + manaBonus;
    }

    public void applyBuff(Entity user) {
        user.maxMana += manaBonus;
        user.mana += manaBonus;
        
        if(getGamePanel() != null) {
            getGamePanel().playSE(2);
            getGamePanel().ui.addMessage("Max Mana +" + manaBonus + "!");
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
    
    public int getManaBonus() {
        return manaBonus;
    }
}