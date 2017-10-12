/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KaamelottEvent;

import KaamelottCapacities.*;
import KaamelottControl.DisplayText;
import KaamelottControl.GameInterface;
import KaamelottControl.Team;

/**
 *
 * @author Kalo
 */
public class AddCapacity extends Event {
    private final int Type=0;
    private Capacity capacity;
    private Team team;
    public int getType() {
        return Type;
    }


    public AddCapacity(GameInterface gi, int nbCapacity) {
        super(gi);
        this.capacity = getCapacityI(nbCapacity);
    }
    
    
    
    public void doAddCapacity(Team team){
        int max=team.getTeamNumber();
        String[] mess = new String [max+1];
        mess[0]="Which character should learn "+capacity.getName() +" ?";
        for (int i=0; i<max;i++)
           {
               mess[i+1]=team.getCharacterI(i).getName();
           }
        int nbCharac=gi.getNumber(mess)-1;
        
        team.getCharacterI(nbCharac).addCapacity(capacity);
    }
    
    public Capacity getCapacityI(int nbCapacity){
        Capacity cap;
        switch(nbCapacity){
            case 0: cap=new Spell(40,"Fireball");
                break;
            default:
                cap=new Spell(5,"little Spell");
                break;
                      
        }
        return cap;
    }
}
