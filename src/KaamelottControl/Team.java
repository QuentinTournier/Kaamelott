/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KaamelottControl;
import java.util.ArrayList;
import java.util.List;
import KaamelottCharacter.Character;
import KaamelottCharacter.*;


/**
 *
 * @author p1511544
 */
public class Team {
    private List<Character> characters;
    protected int teamNumber;
    private GameInterface gi;

    
    
    public Team() {
        teamNumber=0;
        characters = new ArrayList<>();

    }

    public Team(GameInterface gi, List<Character> characters, int teamNumber) {
        this.gi =gi;
        this.characters = characters;
        this.teamNumber = teamNumber;
    }
    
    
    public void addCharacterTeam(Character character) {
        characters.add(character);
        teamNumber++;
    }
    
    public void fillTeam(int number) {
        for (int i = 0; i < number; i++) {
            this.addCharacterTeam(new Thief(gi, "thief" + i));
        }
    }
    
    public List<Character> getCharacters(){
        return characters;
    }
    
    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
    
    public int getTeamNumber() {
        return teamNumber;
    }
    
    public Character getCharacterI(int num){
        
        return this.characters.get(num);
    }
    
    public boolean isTeamAlive()
    {
        for(int i=0 ; i<(characters.size()) ;i++)
        {
            if(characters.get(i).isAlive())
            {
                return true;
            }
        }
        return false;
    }
    public void equipCharacter(){
        int max=characters.size();
        String[] mess = new String [max+2];
        mess[0] = "Which character do you wish to equip ?";
        for (int i=0;i<max;i++)
           {
               mess[i+1] = this.getCharacterI(i).getName();
           }
        mess[max+1]="Return";
        int value= gi.getNumber(mess)-1;
        if (value==max+1)
            return ;
        characters.get(value).equip();
    }
    
    public void takeObject(){
        int max=characters.size();
        String[] mess = new String [max+2];
        mess[0] = "Which character do you wish to use a consumable with ?";
        for (int i=0;i<max;i++)
            {
                mess[i+1] = this.getCharacterI(i).getName()+"-"+this.getCharacterI(i).getHp()+"HP";
            }
        mess[max+1] = "Return";
        int value=gi.getNumber(mess);
        if (value==max+1)
            return ;
        characters.get(value-1).useConsumable();
    }        
    
    
    public void showStats(){
        int max=characters.size();
        String[] mess = new String [max+2];
        mess[0] = "Which character do you wish to see stats ?";
        for (int i=0;i<max;i++)
            {
                mess[i+1] = this.getCharacterI(i).getName()+"-"+this.getCharacterI(i).getHp()+"HP";
            }
        mess[max+1] = " Return";
        int value=gi.getNumber(mess);
        if (value==max+1)
            return ;
        characters.get(value-1).stats();
    }
     




    public void showHp(){
        int max=characters.size();
        String mess="You have:  ";
        for (int i=0;i<max;i++)
            {
                   mess=mess+this.getCharacterI(i).getName()+": "+this.getCharacterI(i).getHp()+"HP";
                   if(i<max-1)
                       mess=mess+"//";
            }
        gi.display(mess);
        
        
    }




}
