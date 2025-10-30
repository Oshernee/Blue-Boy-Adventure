package entity.decorator;

import entity.Entity;
import main.GamePanel;

public class EquipmentBuilder {
    
    private Entity equipment;
    private GamePanel gp;
    private String presetName = ""; // For preset naming only
    
    private EquipmentBuilder(Entity baseEquipment) {
        this.equipment = baseEquipment;
        this.gp = baseEquipment.getGp();
    }
    
    public static EquipmentBuilder create(Entity baseEquipment) {
        return new EquipmentBuilder(baseEquipment);
    }
    
    // Individual decorator methods - NO NAMING
    public EquipmentBuilder withDamageBuff(int bonus) {
        equipment = new DamageBuffDecorator(equipment, bonus);
        return this;
    }
    
    public EquipmentBuilder withDefenseBuff(int bonus) {
        equipment = new DefenseBuffDecorator(equipment, bonus);
        return this;
    }
    
    public EquipmentBuilder withLifeSteal(int percent) {
        equipment = new LifeStealDecorator(equipment, percent);
        return this;
    }
    
    public EquipmentBuilder withCriticalChance(int percent) {
        equipment = new CriticalChanceDecorator(equipment, percent);
        return this;
    }
    
    public EquipmentBuilder withElement(ElementalDecorator.ElementType elementType) {
        equipment = new ElementalDecorator(equipment, elementType);
        return this;
    }
    
    public EquipmentBuilder withMana(int manaBonus) {
        equipment = new ManaBuffDecorator(equipment, manaBonus);
        return this;
    }
    
    public EquipmentBuilder withHealth(int healthBonus) {
        equipment = new HealthBuffDecorator(equipment, healthBonus);
        return this;
    }
    
    public EquipmentBuilder withSpeed(int speedBonus) {
        equipment = new SpeedBuffDecorator(equipment, speedBonus);
        return this;
    }
    
    // Preset methods - THESE SET THE NAME
    public EquipmentBuilder asCommon() {
        this.presetName = "Common";
        return this.withDamageBuff(1);
    }
    
    public EquipmentBuilder asUncommon() {
        this.presetName = "Uncommon";
        return this.withDamageBuff(2)
                   .withDefenseBuff(1);
    }
    
    public EquipmentBuilder asRare() {
        this.presetName = "Rare";
        return this.withDamageBuff(3)
                   .withCriticalChance(10);
    }
    
    public EquipmentBuilder asEpic() {
        this.presetName = "Epic";
        return this.withDamageBuff(5)
                   .withCriticalChance(15)
                   .withHealth(2);
    }
    
    public EquipmentBuilder asLegendary() {
        this.presetName = "Legendary";
        return this.withDamageBuff(7)
                   .withCriticalChance(25)
                   .withLifeSteal(12);
    }
    
    public EquipmentBuilder asVampiric() {
        this.presetName = "Vampiric";
        return this.withDamageBuff(3)
                   .withLifeSteal(20)
                   .withElement(ElementalDecorator.ElementType.POISON);
    }
    
    public EquipmentBuilder asBerserker() {
        this.presetName = "Berserker";
        return this.withDamageBuff(6)
                   .withCriticalChance(30)
                   .withSpeed(2);
    }
    
    public EquipmentBuilder asTank() {
        this.presetName = "Tank";
        return this.withDefenseBuff(5)
                   .withHealth(6);
    }
    
    public EquipmentBuilder asMage() {
        this.presetName = "Mage";
        return this.withDamageBuff(2)
                   .withMana(6)
                   .withElement(ElementalDecorator.ElementType.LIGHTNING);
    }
    
    public EquipmentBuilder asFlaming() {
        this.presetName = "Flaming";
        return this.withElement(ElementalDecorator.ElementType.FIRE)
                   .withDamageBuff(5)
                   .withCriticalChance(15);
    }
    
    public EquipmentBuilder asFrozen() {
        this.presetName = "Frozen";
        return this.withElement(ElementalDecorator.ElementType.ICE)
                   .withDamageBuff(3)
                   .withDefenseBuff(3)
                   .withHealth(3);
    }
    
    public Entity build() {
        // Apply preset name if one was set
        if(!presetName.isEmpty()) {
            // Get the base name (original equipment name without any prefixes)
            String originalName = getOriginalName(equipment.name);
            equipment.name = presetName + " " + originalName;
        }
        return equipment;
    }
    
    // Helper method to extract original equipment name by removing ALL preset prefixes
    private String getOriginalName(String currentName) {
        String name = currentName.trim();
        
        // List of all possible preset prefixes
        String[] prefixes = {
            "Common ", "Uncommon ", "Rare ", "Epic ", "Legendary ",
            "Vampiric ", "Berserker ", "Tank ", "Mage ",
            "Flaming ", "Frozen ", "Thundering ", "Poisonous "
        };
        
        // Keep removing prefixes until none are found
        boolean foundPrefix;
        do {
            foundPrefix = false;
            for(String prefix : prefixes) {
                if(name.startsWith(prefix)) {
                    name = name.substring(prefix.length()).trim();
                    foundPrefix = true;
                    break; // Start over from the beginning
                }
            }
        } while(foundPrefix);
        
        return name;
    }
}