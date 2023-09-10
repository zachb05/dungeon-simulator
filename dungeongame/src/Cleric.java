
import java.util.Random;

public class Cleric extends Character{
    
    public Cleric( String name ) {
        super(name);
    }
    
    public Cleric( String name, int level,
                     int strength, int dexterity,
                     int intelligence, int wisdom ) {
        super(name, level, strength, dexterity, intelligence, wisdom);
    }
    
    public void attack( Character c ) {
        Random r = new Random();
        
        int r1 = r.nextInt(6) + 1, r2 = r.nextInt(6) + 1;
        c.setHP(c.getHP() - (Math.abs((r1 + r2 - 6)*(c.getStrength() + c.getDexterity() + 
                c.getLevel())/6 + 2)));
        
        if ( c.getHP() <= 0 ) {
                c.setIsFainted(true);
                c.setHP(0);
        }
    }
    
    public void heal( Character c ) {
        if ( c.getIsFainted() ) {
            return;
        }
        
        Random r = new Random();
        
        int r1 = r.nextInt(8) + 1;
        c.setHP(c.getHP() + ((r1 + c.getWisdom() + c.getLevel() - 2) / 2));
        
        if (c.getHP() > c.getMaxHP()) {
            c.setHP(c.getMaxHP());
        }
    }
    
    public void levelUp() {
        super.setLevel(super.getLevel() + 1);
        super.setStrength(super.getStrength() + 1);
        super.setDexterity(super.getDexterity() + 1);
        super.setIntelligence(super.getIntelligence() + 1);
        super.setWisdom(super.getWisdom() + 2);
        super.setHP(super.getMaxHP());
    }
    
    @Override
    public String toString() {
        return "Level " + super.getLevel() + " cleric named " + super.getName() + 
                " with " + super.getStrength() + " strength, " + super.getDexterity()
                + " dexterity, " + super.getIntelligence() + " intelligence, and "
                + super.getWisdom() + " wisdom.";
    }
}
