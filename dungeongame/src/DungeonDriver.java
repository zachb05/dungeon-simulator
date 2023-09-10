import java.util.*;

public class DungeonDriver {

    public static void main(String[] args) {
        
        Scanner keys = new Scanner(System.in);
        
        System.out.println("Welcome to Dungeon Mastery!");
        
        // Part A
        Character[] player = new Character[2];
        for ( int i = 1; i < 3 ; i++ ) {
            System.out.print("\n\tEnter the name of character " + i + ": ");
            String name = keys.next();
            
            int subclass;
            do {
                    System.out.print("\n\t1. Fighter\n\t2. Rogue\n\t3. Cleric\n\t4. Wizard\nEnter the subclass: ");
                    subclass = keys.nextInt();
                    
                    if ( subclass < 1 || subclass > 4 ) {
                            System.out.println("Invalid!");
                    }
            } while( subclass < 1 || subclass > 4);
            
            
            int strength, dexterity, intelligence, wisdom;
            do {
                    System.out.println("\nDistribute 24 points towards strength, dexterity, intelligence, and wisdom");

                    System.out.print("Strength: ");
                    strength = keys.nextInt();
                    System.out.print("Dexterity: ");
                    dexterity = keys.nextInt();
                    System.out.print("Intelligence: ");
                    intelligence = keys.nextInt();
                    System.out.print("Wisdom: ");
                    wisdom = keys.nextInt();

                    if ( strength + dexterity + intelligence + wisdom != 24 ) {
                            System.out.println("Invalid! Must sum to 24");
                    }
            } while ( strength + dexterity + intelligence + wisdom != 24 );
            
            switch ( subclass ) {
                    case 1:
                            player[i - 1] = new Fighter(name, 1, strength, dexterity, intelligence, wisdom);
                            break;
                    case 2:
                            player[i - 1] = new Rogue(name, 1, strength, dexterity, intelligence, wisdom);
                            break;
                    case 3:
                            player[i - 1] = new Cleric(name, 1, strength, dexterity, intelligence, wisdom);
                            break;
                    case 4:
                            player[i - 1] = new Wizard(name, 1, strength, dexterity, intelligence, wisdom);
                            break;
            }
            
            System.out.println(player[i - 1].toString());
        }
             
        Rogue[] eFight1 = new Rogue[3];
        for ( int i = 1; i < 4; i++ ) {
                eFight1[i - 1] = new Rogue("Enemy Rogue " + i, 1, 3, 3, 3, 3);
        }
        
        Character[] eFight2 = new Character[2];
        eFight2[0] = new Wizard("Enemy Wizard 1", 3, 6, 6, 6, 6);
        eFight2[1] = new Cleric("Enemy Wizard 1", 3, 6, 6, 6, 6);
        
        Fighter eFight3 = new Fighter("Enemy Fighter 1", 6, 10, 10, 10, 10);

        Battle f1 = new Battle(player, (Character[])eFight1);
        f1.start();
        
        if(!f1.isWin) {
            return;
        }
        for (Character chara : player) {
            chara.levelUp();
        }
        
        System.out.println("\t\tSecond battle commences! (Press any button to continue)"); keys.next();
        Battle f2 = new Battle(player, eFight2);
        f2.start();
        
        if(!f2.isWin) {
            return;
        }
        for (Character chara : player) {
            chara.levelUp();
        }
        
        System.out.println("\t\tThird battle commences! (Press any button to continue)"); keys.next();
        Character[] eFight3Arr = {eFight3};
        Battle f3 = new Battle(player, eFight3Arr);
        f3.start();
        
        if(!f3.isWin) {
            return;
        }
        for (Character chara : player) {
            chara.levelUp();
        }
    }
}
