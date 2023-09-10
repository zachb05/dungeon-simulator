import java.util.Random;

public class Fighter extends Character{
    
    public Fighter( String name ) {
        super(name);
    }
    
    public Fighter( String name, int level,
                     int strength, int dexterity,
                     int intelligence, int wisdom ) {
        super(name, level, strength, dexterity, intelligence, wisdom);
    }
    
    public void attack( Character c ) {
        Random r = new Random();
        
        int r1 = r.nextInt(6) + 1, r2 = r.nextInt(6) + 1;
        c.setHP(c.getHP() - (Math.abs((r1 + r2 - 6)*(c.getStrength() + c.getDexterity() + 
                c.getLevel())/6 + 6)));
        
        if ( c.getHP() <= 0 ) {
                c.setIsFainted(true);
                c.setHP(0);
        }
    }
    
    public void levelUp() {
        super.setLevel(super.getLevel() + 1);
        super.setStrength(super.getStrength() + 2);
        super.setDexterity(super.getDexterity() + 1);
        super.setIntelligence(super.getIntelligence() + 1);
        super.setWisdom(super.getWisdom() + 1);
        super.setHP(super.getMaxHP());
    }
    
    @Override
    public String toString() {
        return "Level " + super.getLevel() + " fighter named " + super.getName() + 
                " with " + super.getStrength() + " strength, " + super.getDexterity()
                + " dexterity, " + super.getIntelligence() + " intelligence, and "
                + super.getWisdom() + " wisdom.";
    }
}
