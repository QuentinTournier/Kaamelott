/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KaamelottEvent;

import KaamelottControl.DisplayText;
import KaamelottControl.GameInterface;
import KaamelottControl.Team;
import KaamelottCharacter.*;

/**
 *
 * @author nitnek
 */
public class CreateCharacter extends Event{
    

    private final int type=1;
    private static int nbCharac=0;
    
    public CreateCharacter(GameInterface gi) {
        super(gi);
    }

    public int getType() {
        return type;
    }

    public String askName(){
        String message="Chose the name of your hero";
        return gi.getName(message);
    }
    public int askType(){
        
        int min=1;
        int max=5;
        String [] mess = new String[6];
        mess[0] = "Chose a class";
        mess[1] ="Crossbowman";
        mess[2] ="Druid";
        mess[3] ="Knight";
        mess[4] ="Thief";
        mess[5] ="Warrior";

        return gi.getNumber(mess);
    }
    
    public void addCharac(Team team){
        nbCharac++;
        KaamelottCharacter.Character charac;
        int choice=askType();
        String name=askName();
        switch (choice) {
            case 1:  charac = new Crossbowman(gi, name);
                     break;
            case 2:  charac = new Druid(gi, name);
                     break;
            case 3:  charac = new Knight(gi, name);
                     break;
            case 4:  charac = new Thief(gi, name);
                     break;
            case 5:  charac = new Warrior(gi, name);
                     break;
            default: {System.out.println("error");
                    charac=new Crossbowman(gi, name);
                     break;}
        }
    
    
        charac.addPotions();                
        team.addCharacterTeam(charac);
        if (nbCharac>4)
            charac.addKillerParchment();
            
    }
    
    
    
}
