/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KaamelottEvent;

import KaamelottCharacter.Characteristic;
import KaamelottControl.DisplayText;
import KaamelottControl.Team;
import KaamelottItemization.*;

/**
 *
 * @author nitnek
 */
public class AddItem implements Event{
    private final int type=4;
    private Team team;
    private int nbItem; 

    public AddItem(Team team, int nbItem) {
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
    DisplayText display=new DisplayText();
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
            String mess="Chose which character will get this:"+item.getName()+"(+"+item.getValue()+" "+characteristic+") \n";
            for (int i=0;i<team.getTeamNumber();i++)
            {
                mess=mess+i+"-"+team.getCharacterI(i).getName()+"\n";
            }
            
            String messError="Please chose a number between 0 and "+team.getTeamNumber();
            nbCharac=display.getNumber(0,team.getTeamNumber()-1,mess,messError);
            
        }
        team.getCharacterI(nbCharac).addEquipment(item);
        display.display(team.getCharacterI(nbCharac).getName()+" obtained "+item.getName());
    }
}
