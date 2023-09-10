
import java.util.Random;

public class Wizard extends Character{
    
    public Wizard( String name ) {
        super(name);
    }
    
    public Wizard( String name, int level,
                     int strength, int dexterity,
                     int intelligence, int wisdom ) {
        super(name, level, strength, dexterity, intelligence, wisdom);
    }
    
    public void attack( Character c ) {
        Random r = new Random();
        
        int r1 = r.nextInt(12) + 1;
        c.setHP(c.getHP() - (Math.abs((r1 - 4)*(c.getDexterity() + c.getIntelligence() + 
                c.getLevel())/8)));
        
        if ( c.getHP() <= 0 ) {
                c.setIsFainted(true);
                c.setHP(0);
        }
    }
    
    public void multiAttack( Character[] c) {
        Random r = new Random();
        
        for ( Character chara : c) {
                int r1 = r.nextInt(6) + 1;
                chara.setHP(chara.getHP() - (Math.abs(r1 * (chara.getDexterity()
                        + chara.getWisdom() + chara.getLevel()) / 6)));
                
                if ( chara.getHP() <= 0 ) {
                        chara.setIsFainted(true);
                        chara.setHP(0);
                }
        }
    }
    
    public void heal( Character c ) {
        if ( c.getIsFainted() ) {
                return;
        }
        
        Random r = new Random();
        
        int r1 = r.nextInt(8) + 1;
        c.setHP(c.getHP() + ((r1 + c.getWisdom() + c.getLevel()) / 3));
        
        if (c.getHP() > c.getMaxHP()) {
            c.setHP(c.getMaxHP());
        }
    }
    
    public void levelUp() {
        super.setLevel(super.getLevel() + 1);
        super.setStrength(super.getStrength() + 1);
        super.setDexterity(super.getDexterity() + 1);
        super.setIntelligence(super.getIntelligence() + 2);
        super.setWisdom(super.getWisdom() + 1);
        super.setHP(super.getMaxHP());
    }
    
    @Override
    public String toString() {
        return "Level " + super.getLevel() + " wizard named " + super.getName() + 
                " with " + super.getStrength() + " strength, " + super.getDexterity()
                + " dexterity, " + super.getIntelligence() + " intelligence, and "
                + super.getWisdom() + " wisdom.";
    }
}
