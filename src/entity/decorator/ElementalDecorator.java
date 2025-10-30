package entity.decorator;

import entity.Entity;

public class ElementalDecorator extends EquipmentDecorator {
    
    public enum ElementType {
        FIRE("Fire", 3),
        ICE("Ice", 2),
        LIGHTNING("Lightning", 4),
        POISON("Poison", 2);
        
        public final String name;
        public final int bonusDamage;
        
        ElementType(String name, int bonusDamage) {
            this.name = name;
            this.bonusDamage = bonusDamage;
        }
    }
    
    private ElementType elementType;
    
    public ElementalDecorator(Entity equipment, ElementType elementType) {
        super(equipment);
        this.elementType = elementType;

        this.attackValue = baseEquipment.attackValue + elementType.bonusDamage;

        updateDescription();

        this.price = (int)(baseEquipment.price * 1.8);
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
                          "\n+" + elementType.name + " Damage: +" + 
                          elementType.bonusDamage + " ATK";
    }
    
    @Override
    public boolean use(Entity user) {
        boolean result = super.use(user);
        
        if(result && getGamePanel() != null) {
            switch(elementType) {
                case FIRE:
                    getGamePanel().playSE(10); 
                    break;
                case ICE:
                case LIGHTNING:
                case POISON:
                    getGamePanel().playSE(3); 
                    break;
            }
        }
        
        return result;
    }
    
    public ElementType getElementType() {
        return elementType;
    }
}