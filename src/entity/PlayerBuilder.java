package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;

public class PlayerBuilder {
    private GamePanel gp;
    private KeyHandler keyH;
    
    // Position attributes
    private int worldX;
    private int worldY;
    private int currentMap;
    private int currentArea;
    private String direction = "down";
    
    // Screen position
    private int screenX;
    private int screenY;
    
    // Status attributes
    private int level = 1;
    private int maxLife = 10;
    private int life;
    private int maxMana = 8;
    private int mana;
    private int ammo = 10;
    private int strength = 1;
    private int dexterity = 1;
    private int exp = 0;
    private int nextLevelExp = 4;
    private int coin = 40;
    
    // Speed
    private int defaultSpeed = 4;
    private int speed;
    
    // Equipment
    private Entity currentWeapon;
    private Entity currentShield;
    private Entity currentLight;
    private Projectile projectile;
    
    // Solid area
    private Rectangle solidArea;
    private int solidAreaDefaultX = 8;
    private int solidAreaDefaultY = 16;
    
    // State flags
    private boolean invincible = false;
    private boolean attackCanceled = false;
    private boolean lightUpdated = false;
    
    /**
     * Private constructor - use static factory method
     */
    private PlayerBuilder(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        // Calculate screen position
        this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        // Set default position
        this.worldX = gp.tileSize * 23;
        this.worldY = gp.tileSize * 21;
        this.currentMap = 0;
        this.currentArea = gp.outside;
        
        // Set default speed
        this.speed = defaultSpeed;
        
        // Set default life and mana
        this.life = maxLife;
        this.mana = maxMana;
        
        // Default solid area
        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidArea.width = 32;
        this.solidArea.height = 32;
        
        // Default equipment
        this.currentWeapon = new OBJ_Sword_Normal(gp);
        this.currentShield = new OBJ_Shield_Wood(gp);
        this.currentLight = null;
        this.projectile = new OBJ_Fireball(gp);
    }
    
    /**
     * Static factory method to create a new builder
     */
    public static PlayerBuilder create(GamePanel gp, KeyHandler keyH) {
        return new PlayerBuilder(gp, keyH);
    }
    
    // Position setters
    public PlayerBuilder setPosition(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        return this;
    }
    
    public PlayerBuilder setWorldX(int worldX) {
        this.worldX = worldX;
        return this;
    }
    
    public PlayerBuilder setWorldY(int worldY) {
        this.worldY = worldY;
        return this;
    }
    
    public PlayerBuilder setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
        return this;
    }
    
    public PlayerBuilder setCurrentArea(int currentArea) {
        this.currentArea = currentArea;
        return this;
    }
    
    public PlayerBuilder setDirection(String direction) {
        this.direction = direction;
        return this;
    }
    
    // Status setters
    public PlayerBuilder setLevel(int level) {
        this.level = level;
        return this;
    }
    
    public PlayerBuilder setMaxLife(int maxLife) {
        this.maxLife = maxLife;
        this.life = maxLife; // Also set current life
        return this;
    }
    
    public PlayerBuilder setLife(int life) {
        this.life = life;
        return this;
    }
    
    public PlayerBuilder setMaxMana(int maxMana) {
        this.maxMana = maxMana;
        this.mana = maxMana; // Also set current mana
        return this;
    }
    
    public PlayerBuilder setMana(int mana) {
        this.mana = mana;
        return this;
    }
    
    public PlayerBuilder setAmmo(int ammo) {
        this.ammo = ammo;
        return this;
    }
    
    public PlayerBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }
    
    public PlayerBuilder setDexterity(int dexterity) {
        this.dexterity = dexterity;
        return this;
    }
    
    public PlayerBuilder setExp(int exp) {
        this.exp = exp;
        return this;
    }
    
    public PlayerBuilder setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
        return this;
    }
    
    public PlayerBuilder setCoin(int coin) {
        this.coin = coin;
        return this;
    }
    
    // Speed setters
    public PlayerBuilder setSpeed(int speed) {
        this.defaultSpeed = speed;
        this.speed = speed;
        return this;
    }
    
    public PlayerBuilder setDefaultSpeed(int defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
        this.speed = defaultSpeed;
        return this;
    }
    
    // Equipment setters
    public PlayerBuilder setCurrentWeapon(Entity weapon) {
        this.currentWeapon = weapon;
        return this;
    }
    
    public PlayerBuilder setCurrentShield(Entity shield) {
        this.currentShield = shield;
        return this;
    }
    
    public PlayerBuilder setCurrentLight(Entity light) {
        this.currentLight = light;
        return this;
    }
    
    public PlayerBuilder setProjectile(Projectile projectile) {
        this.projectile = projectile;
        return this;
    }
    
    // Solid area setter
    public PlayerBuilder setSolidArea(int x, int y, int width, int height) {
        this.solidArea.x = x;
        this.solidArea.y = y;
        this.solidArea.width = width;
        this.solidArea.height = height;
        this.solidAreaDefaultX = x;
        this.solidAreaDefaultY = y;
        return this;
    }
    
    // State flag setters
    public PlayerBuilder setInvincible(boolean invincible) {
        this.invincible = invincible;
        return this;
    }
    
    public PlayerBuilder setAttackCanceled(boolean attackCanceled) {
        this.attackCanceled = attackCanceled;
        return this;
    }
    
    public PlayerBuilder setLightUpdated(boolean lightUpdated) {
        this.lightUpdated = lightUpdated;
        return this;
    }
    
    // Preset configurations
    public PlayerBuilder withBeginnerStats() {
        return this.setLevel(1)
                   .setMaxLife(10)
                   .setMaxMana(8)
                   .setStrength(1)
                   .setDexterity(1)
                   .setExp(0)
                   .setNextLevelExp(4)
                   .setCoin(40);
    }
    
    public PlayerBuilder withIntermediateStats() {
        return this.setLevel(5)
                   .setMaxLife(20)
                   .setMaxMana(16)
                   .setStrength(5)
                   .setDexterity(5)
                   .setExp(0)
                   .setNextLevelExp(20)
                   .setCoin(200);
    }
    
    public PlayerBuilder withAdvancedStats() {
        return this.setLevel(10)
                   .setMaxLife(30)
                   .setMaxMana(24)
                   .setStrength(10)
                   .setDexterity(10)
                   .setExp(0)
                   .setNextLevelExp(60)
                   .setCoin(1000);
    }
    
    public PlayerBuilder atStartingPosition() {
        return this.setPosition(gp.tileSize * 23, gp.tileSize * 21)
                   .setCurrentMap(0)
                   .setDirection("down");
    }
    
    public PlayerBuilder atBlueGemPosition() {
        return this.setPosition(gp.tileSize * 25, gp.tileSize * 9)
                   .setCurrentMap(3)
                   .setDirection("down");
    }
    
    public PlayerBuilder withBasicEquipment() {
        return this.setCurrentWeapon(new OBJ_Sword_Normal(gp))
                   .setCurrentShield(new OBJ_Shield_Wood(gp))
                   .setCurrentLight(null)
                   .setProjectile(new OBJ_Fireball(gp));
    }
    
    public PlayerBuilder withAdvancedEquipment() {
        return this.setCurrentWeapon(new OBJ_Axe(gp))
                   .setCurrentShield(new OBJ_Shield_Blue(gp))
                   .setCurrentLight(new OBJ_Lantern(gp))
                   .setProjectile(new OBJ_Fireball(gp));
    }
    
    /**
     * Build and return the Player instance
     */
    public Player build() {
        return new Player(this);
    }
    
    // Package-private getters for Player constructor
    GamePanel getGp() { return gp; }
    KeyHandler getKeyH() { return keyH; }
    int getWorldX() { return worldX; }
    int getWorldY() { return worldY; }
    int getCurrentMap() { return currentMap; }
    int getCurrentArea() { return currentArea; }
    String getDirection() { return direction; }
    int getScreenX() { return screenX; }
    int getScreenY() { return screenY; }
    int getLevel() { return level; }
    int getMaxLife() { return maxLife; }
    int getLife() { return life; }
    int getMaxMana() { return maxMana; }
    int getMana() { return mana; }
    int getAmmo() { return ammo; }
    int getStrength() { return strength; }
    int getDexterity() { return dexterity; }
    int getExp() { return exp; }
    int getNextLevelExp() { return nextLevelExp; }
    int getCoin() { return coin; }
    int getDefaultSpeed() { return defaultSpeed; }
    int getSpeed() { return speed; }
    Entity getCurrentWeapon() { return currentWeapon; }
    Entity getCurrentShield() { return currentShield; }
    Entity getCurrentLight() { return currentLight; }
    Projectile getProjectile() { return projectile; }
    Rectangle getSolidArea() { return solidArea; }
    int getSolidAreaDefaultX() { return solidAreaDefaultX; }
    int getSolidAreaDefaultY() { return solidAreaDefaultY; }
    boolean isInvincible() { return invincible; }
    boolean isAttackCanceled() { return attackCanceled; }
    boolean isLightUpdated() { return lightUpdated; }
}
