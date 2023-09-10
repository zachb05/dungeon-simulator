import java.util.*;

public class Battle {
    private ArrayList<Character> players = new ArrayList<Character>();
    private ArrayList<Character> enemies = new ArrayList<Character>();;
    private int TURN = 0;
    private boolean isPlayer = true;
    public boolean isWin = false;
    
    Battle( Character[] players, Character[] enemies ) {
        for ( Character player : players ) {
            this.players.add(player);
        }
        
        for ( Character enemy : enemies ) {
            this.enemies.add(enemy);
        }
    }
    
    // Starts the game, continues until one side wins
    public void start() {
        System.out.println("Battle starts!");
        
        while (this.isOver() == false) {
            if ( isPlayer ) {
                this.turnPlayer();
            } else {
                this.turnCPU();
            }
        }
        
        if (isWinner()) {
            System.out.println("\nHurrah, you won!");
            isWin = true;
        } else {
            System.out.println("\nThe enemies got the better of you, try again next time...");
            isWin = false;
        }
    }
    
    private void turnPlayer() {
        System.out.println("\nTurn: " + TURN);
        System.out.println("\tActive Character: " + getActiveChara().toString());
        
        if (getActiveChara() instanceof Fighter) {
            fighterTurn((Fighter)this.getActiveChara());
        } else if (getActiveChara() instanceof Rogue) {
            rogueTurn((Rogue)this.getActiveChara());
        } else if (getActiveChara() instanceof Cleric) {
            clericTurn((Cleric)this.getActiveChara());
        } else if (getActiveChara() instanceof Wizard) {
            wizardTurn((Wizard)getActiveChara());
        }
        isPlayer = false;
    }
    
    private void turnCPU() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            
        }
        this.turnPlayer();
        isPlayer = true;
        TURN++;
    }
    
    // Gets active character based on if its player or enemies' turn
    private Character getActiveChara() {
        return isPlayer ? players.get(TURN % players.size()) : 
                enemies.get(TURN % enemies.size());
    }
    
    private void fighterTurn( Fighter fighter ) {
        if ( isPlayer ) {
            Scanner keys = new Scanner(System.in);
            
            System.out.println("\nFighters can only attack! (Enter any button to continue)");
            keys.next();
            System.out.println("Fighter " + fighter.getName() + " attacks!");
            
            int choice = chooseTarget(true);
            fighter.attack(enemies.get(choice));
            battleReport(enemies.get(choice), choice);
        } else {
            Random r = new Random();
            
            int target = r.nextInt(players.size());
            System.out.println(fighter.getName() + " chooses to attack " + players.get(target).getName() + "!");
            
            fighter.attack(players.get(target));
            battleReport(players.get(target), target);
        }
    }
    
    private void rogueTurn ( Rogue rogue ) {
        if ( isPlayer ) {
            Scanner keys = new Scanner(System.in);
            
            int choice;
            do {
                System.out.println("\n\t1. Attack\n\t2. Heal");
                choice = keys.nextInt();
                if( choice < 1 || choice > 2 ) {
                    System.out.println("Invalid! Try again...");
                }
            } while( choice < 1 || choice > 2 );
            
            switch (choice) {
                case 1:
                    System.out.println(this.getActiveChara().getName() + " chooses to attack!");
                    choice = chooseTarget(true);
                    
                    rogue.attack(enemies.get(choice));
                    battleReport(enemies.get(choice), choice);
                    break;
                case 2:
                    System.out.println(this.getActiveChara().getName() + " chooses to heal!");
                    choice = chooseTarget(false);
                    
                    rogue.heal(players.get(choice));
                    battleReport(players.get(choice), choice);
                    break;
            }
        } else {
            Random r = new Random();
            
            int move = r.nextInt(2), target;
            switch (move) {
                case 0: 
                    target = r.nextInt(players.size());
                    System.out.println(this.getActiveChara().getName() + " chooses to attack " + players.get(target).getName() + "!");
                    
                    rogue.attack(players.get(target));
                    battleReport(players.get(target), target);
                    break;
                case 1:
                    target = r.nextInt(enemies.size());
                    System.out.println(this.getActiveChara().getName() + " chooses to heal " + enemies.get(target).getName() + "!");
                    
                    rogue.heal(enemies.get(target));
                    battleReport(enemies.get(target), target);
                    break;
            }
        }
    }
    
    private void clericTurn( Cleric cleric ) {
        if ( isPlayer ) {
            Scanner keys = new Scanner(System.in);
            
            int choice;
            do {
                System.out.println("\n\t1. Attack\n\t2. Heal");
                choice = keys.nextInt();
                if( choice < 1 || choice > 2 ) {
                    System.out.println("Invalid! Try again...");
                }
            } while( choice < 1 || choice > 2 );
            
            switch (choice) {
                case 1:
                    System.out.println(this.getActiveChara().getName() + " chooses to attack!");
                    choice = chooseTarget(true);
                    
                    cleric.attack(enemies.get(choice));
                    battleReport(enemies.get(choice), choice);
                    break;
                case 2:
                    System.out.println(this.getActiveChara().getName() + " chooses to heal!");
                    choice = chooseTarget(false);
                    
                    cleric.heal(players.get(choice));
                    battleReport(players.get(choice), choice);
                    break;
            }
        } else {
            Random r = new Random();
            
            int move = r.nextInt(2), target;
            switch (move) {
                case 0: 
                    target = r.nextInt(players.size());
                    System.out.println(this.getActiveChara().getName() + " chooses to attack " + players.get(target).getName() + "!");
                    
                    cleric.attack(players.get(target));
                    battleReport(players.get(target), target);
                    break;
                case 1:
                    target = r.nextInt(enemies.size());
                    System.out.println(this.getActiveChara().getName() + " chooses to heal " + enemies.get(target).getName() + "!");
                    
                    cleric.heal(enemies.get(target));
                    battleReport(enemies.get(target), target);
                    break;
            }
        }
    }
    
    private void wizardTurn( Wizard wizard ) {
        if ( isPlayer ) {
            Scanner keys = new Scanner(System.in);
            
            int choice;
            do {
                System.out.println("\n\t1. Attack\n\t2. Heal\n\t3. Multi Attack");
                choice = keys.nextInt();
                if( choice < 1 || choice > 3 ) {
                    System.out.println("Invalid! Try again...");
                }
            } while( choice < 1 || choice > 3 );
            
            switch (choice) {
                case 1:
                    System.out.println(this.getActiveChara().getName() + " chooses to attack!");
                    choice = chooseTarget(true);
                    
                    wizard.attack(enemies.get(choice));
                    battleReport(enemies.get(choice), choice);
                    break;
                case 2:
                    System.out.println(this.getActiveChara().getName() + " chooses to heal!");
                    choice = chooseTarget(false);
                    
                    wizard.heal(enemies.get(choice));
                    battleReport(enemies.get(choice), choice);
                    break;
                case 3:
                    System.out.println(this.getActiveChara().getName() + " chooses to multi attack!");
                    
                    Character[] targets = new Character[enemies.size()];
                    for (int i = 0; i < targets.length; i++) {
                        targets[i] = enemies.get(i);
                    }
                    
                    wizard.multiAttack(targets);
                    enemies.clear();
                    for (Character chara : targets) {
                        enemies.add(chara);
                    }
                    
                    for (int i = 0; i < enemies.size(); i++) {
                        battleReport(enemies.get(i), i);
                    }
                    break;
            }
        } else {
            Random r = new Random();
            
            int move = r.nextInt(3), target;
            switch (move) {
                case 0: 
                    target = r.nextInt(players.size());
                    System.out.println(this.getActiveChara().getName() + " chooses to attack " + players.get(target).getName() + "!");
                    
                    wizard.attack(players.get(target));
                    battleReport(players.get(target), target);
                    break;
                case 1:
                    target = r.nextInt(enemies.size());
                    System.out.println(this.getActiveChara().getName() + " chooses to heal " + enemies.get(target).getName() + "!");
                    
                    wizard.heal(enemies.get(target));
                    battleReport(enemies.get(target), target);
                    break;
                case 2:
                    System.out.println(this.getActiveChara().getName() + " chooses to multi attack!");
                    
                    Character[] targets = new Character[players.size()];
                    for (int i = 0; i < targets.length; i++) {
                        targets[i] = players.get(i);
                    }
                    
                    wizard.multiAttack(targets);
                    players.clear();
                    for (Character chara : targets) {
                        players.add(chara);
                    }
                  
                    for (int i = 0; i < players.size(); i++) {
                        battleReport(players.get(i), i);
                    }
                    break;
            }
        }
    }
    
    // Takes care of fainting, showing resulting HP
    private void battleReport( Character chara, int position ) {
        if (chara.getIsFainted()) {
            System.out.println(chara.getName() + " fainted!");
            
            if (isPlayer) {
                enemies.remove(position);
            } else {
                players.remove(position);
            }
        } else {
            System.out.println(chara.getStatus());
        }
    }
    
    // Prints available targets based on turn and if it is an attack or a heal
    private void printTargets(boolean isAttack) {
        ArrayList<Character> targets = (isPlayer == isAttack) ? enemies : players;
        for ( int i = 0; i < targets.size(); i++ ) {
            System.out.print("\n\t" + (i + 1) + ". " + targets.get(i).getName()
                    + " (" + targets.get(i).getHP() + "/" + targets.get(i).getMaxHP() 
                    + " HP)");
        }
        System.out.println();
    }
    
    private int chooseTarget(boolean isAttack) {
        Scanner keys = new Scanner(System.in);
        
        int choice;
        do {
            System.out.println("Who do you target?");
            printTargets(isAttack);
            choice = keys.nextInt();

            if ( choice < 1 || choice >  (isPlayer == isAttack ? enemies.size() : players.size()) ){
                System.out.println("Invalid! Try again...");
            }
        } while( choice < 1 || choice > (isPlayer ? enemies.size() : players.size()) );
        
        return choice - 1;
    }
    
    // Checks if the game is over
    public boolean isOver() {
        int enemiesFainted = 0;
        for (int i = 0; i < enemies.size() ; i++) {
            enemiesFainted += enemies.get(i).getIsFainted() ? 1 : 0;
        }
        
        int playersFainted = 0;
        for (int i = 0; i < players.size() ; i++) {
            playersFainted += players.get(i).getIsFainted() ? 1 : 0;
        }
        
        return enemiesFainted == enemies.size() || playersFainted == players.size();
    }
    
    // Once over, checks which one is the winner
    public boolean isWinner() {
        return !isPlayer;
    }
}
