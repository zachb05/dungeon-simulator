import java.util.*;

public abstract class Character {
    private String name;
    private int level;
    private int[] stats = new int[4];
    private double HP;
    private boolean isFainted = false;

    public Character(String name) {
        Random r = new Random();
        
        this.name = name;
        this.level = 1;
        this.isFainted = false;
        
        for (int stat : this.stats) {
            stat = r.nextInt(6) + 1;
        }
        
        this.HP =  2*(level + this.min(stats[0], stats[1]) + this.min(stats[2], 
                   stats[3]));
    }

    public Character(String name, int level,
                     int strength, int dexterity,
                     int intelligence, int wisdom) {
        this.name = name;
        this.level = level;
        this.stats[0] = strength;
        this.stats[1] = dexterity;
        this.stats[2] = intelligence;
        this.stats[3] = wisdom;
        this.isFainted = false;
        this.HP = 2*(level + this.min(stats[0], stats[1]) + this.min(stats[2], 
                   stats[3]));
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return this.stats[0];
    }

    public void setStrength(int strength) {
        this.stats[0] = strength;
    }

    public int getDexterity() {
        return this.stats[1];
    }

    public void setDexterity(int dexterity) {
        this.stats[1] = dexterity;
    }

    public int getIntelligence() {
        return this.stats[2];
    }

    public void setIntelligence(int intelligence) {
        this.stats[2] = intelligence;
    }

    public int getWisdom() {
        return this.stats[3];
    }

    public void setWisdom(int wisdom) {
        this.stats[3] = wisdom;
    }

    public double getHP() {
        return this.HP;
    }

    public double getMaxHP() {
        return 2*(level + this.min(stats[0], stats[1]) + this.min(stats[2], 
                   stats[3]));
    }
    public void setHP(double HP) {
        this.HP = HP;
    }

    public boolean getIsFainted() {
        return this.isFainted;
    }

    public void setIsFainted(boolean isFainted) {
        this.isFainted = isFainted;
    }

    public String getStatus() {
        return this.name + " has " + this.HP + " hp remaining";
    }

    public abstract void levelUp();

    public abstract void attack(Character c);
    
    @Override
    public abstract String toString();
    
    private double min( int num1, int num2 ) {
        return (double)(num1 < num2 ? num1 :  num2);
    }
}