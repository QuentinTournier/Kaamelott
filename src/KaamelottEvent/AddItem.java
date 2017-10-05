/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KaamelottEvent;

import KaamelottCharacter.Characteristic;
import KaamelottControl.GameInterface;
import KaamelottControl.Team;
import KaamelottItemization.*;

/**
 *
 * @author nitnek
 */
public class AddItem extends Event{
    private final int type=4;
    private Team team;
    private int nbItem; 

    public AddItem(GameInterface gi, Team team, int nbItem) {
        super(gi);
        this.team = team;
        this.nbItem = nbItem;
    }

    public int getType() {
        return type;
    }
    /*public void characGetsItem(){
        team.getCharacterI(nbCharac).addEquipment(item);
        
    
    }*/
    
    public void doAddItem(){

        int nbCharac=0;
        String characteristic;
        Item item;
    
        switch(nbItem){
            case 1:
                item= new Weapon(30,"Excalibur",10,Characteristic.STRENGTH);
                characteristic="STRENGTH";
                break;
            case 2:  
                item= new Weapon(30,"Butcher's Carver",10,Characteristic.STRENGTH);
                characteristic="STRENGTH";
                break;
            case 3:
                item= new Weapon(35,"Grand Sword",10,Characteristic.STRENGTH);
                characteristic="STRENGTH";
                break;
            case 11:
                item= new Weapon(7,"Magic staff",10,Characteristic.INTELLIGENCE);
                characteristic="INTELLIGENCE";
                break;
            case 21:
                item= new Weapon(20,"dagger",10,Characteristic.DEXTERITY);
                characteristic="DEXTERITY";
                break;
            case 22:
                item= new Weapon(35,"Deadeye",10,Characteristic.DEXTERITY);
                characteristic="DEXTERITY";
                break;
            case 23:
                item= new Weapon(10,"Apprentice Crossbow",10,Characteristic.DEXTERITY);
                characteristic="DEXTERITY";
                break;
            case 24:
                item= new Weapon(30,"Envious Blade",10,Characteristic.DEXTERITY);
                characteristic="DEXTERITY";
                break;
            case 31:
                item= new Armor(15,"Deadman's plate",20);
                characteristic="DEFENSE";
                break;
            case 32:
                item= new Armor(20,"Splint Cuirass",30);
                characteristic="DEFENSE";
                break;
            default:
                item= new Armor(0,"Thornmail",10);
                characteristic="DEFENSE";
                break;
        }

        if(nbItem!=1){
            String [] mess = new String[team.getTeamNumber()+1];
            mess[0] ="Chose which character will get this:"+item.getName()+"(+"+item.getValue()+" "+characteristic+") \n";
            for (int i=0;i<team.getTeamNumber();i++)
            {
                mess[i+1] = team.getCharacterI(i).getName();
            }
            nbCharac = gi.getNumber(mess)-1;
            
        }
        team.getCharacterI(nbCharac).addEquipment(item);
        gi.display(team.getCharacterI(nbCharac).getName()+" obtained "+item.getName());
    }
}
